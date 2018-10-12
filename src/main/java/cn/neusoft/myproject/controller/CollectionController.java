/**
 * Copyright (C), 2018, Jerry
 * FileName: CollectionController
 * Author:   jerry
 * Date:     2018/10/3 15:14
 * Description: 收藏表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.neusoft.myproject.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.neusoft.myproject.aspect.Log;
import cn.neusoft.myproject.domain.CollectionDO;
import cn.neusoft.myproject.domain.CollectionRelation;
import cn.neusoft.myproject.service.CollectionService;
import cn.neusoft.myproject.utils.PageUtils;
import cn.neusoft.myproject.utils.Query;
import cn.neusoft.myproject.utils.R;

/**
 * 〈一句话功能简述〉<br>
 * 〈收藏表〉
 *
 * @author jerry
 * @create 2018/10/3
 * @since 1.0.0
 */
@RequestMapping("/common/collection")
@Controller
public class CollectionController extends BaseController {
  @Autowired
  CollectionService collectionService;
  private String prefix = "common/collection";

  @GetMapping("")
  String collection(Model model) {
    return prefix + "/collection";
  }

  @GetMapping("/list")
  @ResponseBody
  PageUtils list(@RequestParam Map<String, Object> params) {
    // 查询列表数据
    Query query = new Query(params);
    List<CollectionRelation> collectionDOList = collectionService.listCollectionRelation(query);
    int total = collectionService.count(query);
    PageUtils pageUtil = new PageUtils(collectionDOList, total);
    return pageUtil;
  }

  @Log("添加收藏记录")
  @GetMapping("/add")
  String add(Model model) {
    return prefix + "/add";
  }

  @Log("保存收藏记录")
  @PostMapping("/save")
  @ResponseBody
  R save(Long fileId) {
    Map query = new HashMap();
    Long userId = getUserId();
    query.put("fileId", fileId);
    List<CollectionDO> list = collectionService.list(query);
    for (CollectionDO collection : list) {
      if (userId.equals(collection.getUserId())) {
        return R.ok("已收藏");
      }
    }
    CollectionDO collection = new CollectionDO();
    collection.setFileId(fileId);
    collection.setUserId(userId);
    collection.setCreateTime(new Date());
    if (collectionService.save(collection) > 0) {
      return R.ok();
    }
    return R.error();
  }

  @Log("删除收藏记录")
  @PostMapping("/remove")
  @ResponseBody
  R remove(Long id) {
    if (collectionService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  @Log("批量收藏记录")
  @PostMapping("/batchRemove")
  @ResponseBody
  R batchRemove(@RequestParam("ids[]") Long[] ids) {
    int r = collectionService.batchremove(ids);
    if (r > 0) {
      return R.ok();
    }
    return R.error();
  }

  @GetMapping("/userlist")
  String userlist() {
    return "user/index/collection";
  }

  @GetMapping("/minecollection")
  @ResponseBody
  PageUtils minecollection(@RequestParam Map<String, Object> params) {
    // 查询列表数据
    params.put("userId", getUser().getUserId());
    Query query = new Query(params);
    List<CollectionRelation> collectionDOList = collectionService.listCollectionRelation(query);
    int total = collectionService.count(query);
    PageUtils pageUtil = new PageUtils(collectionDOList, total);
    return pageUtil;
  }
}
