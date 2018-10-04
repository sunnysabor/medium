package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.RechargeDO;
import com.bootdo.system.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/sys/recharge")
@Controller
public class RechargeController extends BaseController {
    @Autowired
    RechargeService rechargeService;
    private String prefix = "system/recharge";

    @GetMapping("")
    String recharge(Model model) {
        return prefix + "/recharge";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RechargeDO> rechargeDOList = rechargeService.list(query);
        int total = rechargeService.count(query);
        PageUtils pageUtil = new PageUtils(rechargeDOList, total);
        return pageUtil;
    }

    @Log("添加充值记录")
    @GetMapping("/add")
    String add(Model model) {
        return prefix + "/add";
    }

    @Log("保存充值记录")
    @PostMapping("/save")
    @ResponseBody
    R save(RechargeDO recharge) {
        if (rechargeService.save(recharge) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("删除充值记录")
    @PostMapping("/remove")
    @ResponseBody
    R remove(Long id) {
        if (rechargeService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("批量删除充值记录")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] userIds) {
        int r = rechargeService.batchremove(userIds);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/userlist")
    String userlist() {
        return "user/index/recharge";
    }

    @GetMapping("/minerecharge")
    @ResponseBody
    PageUtils minerecharge(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        params.put("userId",getUser().getUserId());
        Query query = new Query(params);
        List<RechargeDO> rechargeDOList = rechargeService.list(query);
        int total = rechargeService.count(query);
        PageUtils pageUtil = new PageUtils(rechargeDOList, total);
        return pageUtil;
    }
}
