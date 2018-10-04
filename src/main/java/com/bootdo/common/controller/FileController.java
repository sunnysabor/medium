package com.bootdo.common.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.FileRelation;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
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
        List<FileDO> sysFileList = sysFileService.list(query);
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
    String post(@PathVariable("id") Long id,Model model) {
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
    public R updateStatus( Long id, String status) {
        sysFileService.updateStatus(id, status);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        String fileName = bootdoConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
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
        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();
    }


}
