package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
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
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (rechargeService.save(recharge) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("删除充值记录")
    @PostMapping("/remove")
    @ResponseBody
    R remove(Long id) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (rechargeService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("批量删除充值记录")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] userIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        int r = rechargeService.batchremove(userIds);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }


}
