package com.bootdo.common.controller;

import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/userFile")
@Controller
public class UserFileController {
	@Autowired
    FileService fileService;

	@GetMapping()
	String userFile() {
		return "userFile/index/main";
	}

	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		//todo 查询所有已审核的多媒体素材
		Query query = new Query(params);
		List<FileDO> fileDOList = fileService.list(query);
		int total = fileService.count(query);
		PageUtils pageUtils = new PageUtils(fileDOList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
//		ContentDO bContentDO = bContentService.get(cid);
//		model.addAttribute("bContent", bContentDO);
//		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
//		return "userFile/index/post";
		return "";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
	return "";
	}
}
