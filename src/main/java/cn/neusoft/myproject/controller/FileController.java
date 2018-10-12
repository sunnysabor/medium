package cn.neusoft.myproject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.neusoft.myproject.config.MediumConfig;
import cn.neusoft.myproject.domain.CommentRelation;
import cn.neusoft.myproject.domain.FileDO;
import cn.neusoft.myproject.domain.FileRelation;
import cn.neusoft.myproject.domain.FileRelationComment;
import cn.neusoft.myproject.domain.RechargeDO;
import cn.neusoft.myproject.service.CommentService;
import cn.neusoft.myproject.service.FileService;
import cn.neusoft.myproject.service.RechargeService;
import cn.neusoft.myproject.utils.DateUtils;
import cn.neusoft.myproject.utils.FileType;
import cn.neusoft.myproject.utils.FileUtil;
import cn.neusoft.myproject.utils.PageUtils;
import cn.neusoft.myproject.utils.Query;
import cn.neusoft.myproject.utils.R;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

    @Autowired
    private FileService sysFileService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private MediumConfig bootdoConfig;
    @Autowired
    private CommentService commentService;

    @GetMapping()
    String sysFile(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/file/file";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        //List<FileRelation> sysFileList = sysFileService.listRelation(query);
        List<FileRelationComment> fileRelationComments = getList(query);
        int total = sysFileService.count(query);
        PageUtils pageUtils = new PageUtils(fileRelationComments, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "common/sysFile/add";
    }

    @GetMapping("/edit")
    String edit(Long id, Model model) {
        FileDO sysFile = sysFileService.get(id);
        model.addAttribute("sysFile", sysFile);
        return "common/sysFile/edit";
    }

    @GetMapping("/open/post/{id}")
    String post(@PathVariable("id") Long id, Model model) {
        FileDO sysFile = sysFileService.getRelation(id);
        model.addAttribute("sysFile", sysFile);
        model.addAttribute("createDate", DateUtils.format(sysFile.getCreateDate
                ()));
        return "user/index/post";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(FileDO sysFile) throws Exception {
        if (StringUtils.isEmpty(sysFile.getUserId())) {
            if (StringUtils.isEmpty(getUserId()))
                throw new Exception("获取用户ID为空");
            sysFile.setUserId(getUserId());
        }
        sysFile.setStatus("toAudit");
        if (sysFileService.save(sysFile) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 转为会员类型
     */
    @PostMapping("/convert")
    @ResponseBody
    public R updateStatus(Long id) {
        FileDO fileDO = sysFileService.get(id);
        fileDO.setFileType("member");
        if (sysFileService.update(fileDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FileDO sysFile) {
        sysFileService.update(sysFile);

        return R.ok();
    }

    /**
     * 修改多媒体状态（待审核 审核不通过 审核通过）
     */
    @PostMapping("/updateStatus")
    @ResponseBody
    public R updateStatus(Long id, String status) {
        sysFileService.updateStatus(id, status);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        String fileName =
                bootdoConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
        if (sysFileService.remove(id) > 0) {
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return R.error("数据库记录删除成功，文件删除失败");
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] ids) {
        sysFileService.batchRemove(ids);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest  request) {
        String fileName = FileUtil.renameToUUID(file.getOriginalFilename());
        FileDO sysFile = new FileDO(FileType.fileType(fileName),
                "/files/" +
                        fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }
        sysFile.setStatus("toAudit");
        sysFile.setUserId(getUserId());
        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();
    }

    @GetMapping("/userlist")
    String userlist() {
        return "user/index/file";
    }

    @GetMapping("/minefile")
    @ResponseBody
    PageUtils minefile(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        params.put("userId", getUser().getUserId());
        Query query = new Query(params);
        List<FileRelationComment> fileRelationComments = getList(query);
        int total = sysFileService.count(query);
        PageUtils pageUtil = new PageUtils(fileRelationComments, total);
        return pageUtil;
    }

    private List<FileRelationComment> getList(Query query) {
        List<FileRelationComment> fileRelationComments = new ArrayList<>();
        List<FileRelation> fileRelationList = sysFileService.listRelation(query);
        for (FileRelation fileRelation : fileRelationList) {
            Map commentQuery = new HashMap();
            commentQuery.put("fileId", fileRelation.getId());
            List<CommentRelation> commentDOS = commentService.listCommentRelation(commentQuery);
            FileRelationComment fileRelationComment = new FileRelationComment();
            fileRelationComment.setCommentDOList(commentDOS);
            fileRelationComment.setFileRelation(fileRelation);
            fileRelationComments.add(fileRelationComment);
        }
        return fileRelationComments;
    }

    /**
     * 下载
     */
    @PostMapping("/download")
    @ResponseBody
    public R download(Long id) {
        //1.先查询该文件是否需要会员
        FileDO fileDO = sysFileService.get(id);
        if ("member".equals(fileDO.getFileType())) {
            //2.再判断该用户是否是会员
            int flag = 0;//默认不是会员
            Map<String, Object> map = new HashMap<>();
            map.put("userId", getUser().getUserId());
            List<RechargeDO> rechargeDOList = rechargeService.list(map);
            for (RechargeDO rechargeDO : rechargeDOList) {
                if (rechargeDO.getBeginTime().before(new Date()) && rechargeDO
                        .getEndTime().after(new Date())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {//不是会员
                return R.error("无权加载，请充值会员");
            }
            return R.ok(fileDO.getUrl());
        }
        return R.ok(fileDO.getUrl());
    }

    //上传征集素材
    @ResponseBody
    @PostMapping("/uploadconsult/{cousultId}")
    R upload(@RequestParam("file") MultipartFile file,@PathVariable("cousultId")
            String cousultId, HttpServletRequest  request) {
        String fileName = FileUtil.renameToUUID(file.getOriginalFilename());
        FileDO sysFile = new FileDO(FileType.fileType(fileName),
                "/files/" +
                        fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }
        sysFile.setStatus("toAudit");
        sysFile.setUserId(getUserId());
        sysFile.setConsultId(cousultId);
        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();
    }
}
