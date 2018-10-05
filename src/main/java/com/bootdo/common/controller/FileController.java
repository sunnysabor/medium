package com.bootdo.common.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.FileRelation;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.RechargeDO;
import com.bootdo.system.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BootdoConfig bootdoConfig;

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
        List<FileRelation> sysFileList = sysFileService.listRelation(query);
        int total = sysFileService.count(query);
        PageUtils pageUtils = new PageUtils(sysFileList, total);
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
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
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
        List<FileRelation> fileRelationList = sysFileService.listRelation(query);
        int total = sysFileService.count(query);
        PageUtils pageUtil = new PageUtils(fileRelationList, total);
        return pageUtil;
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
}
