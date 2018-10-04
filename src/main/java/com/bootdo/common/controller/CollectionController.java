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
package com.bootdo.common.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.domain.CollectionDO;
import com.bootdo.common.domain.CollectionRelation;
import com.bootdo.common.service.CollectionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        //  List<CollectionDO> collectionDOList = collectionService.list(query);
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
    R save(CollectionDO collection) {
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
}
