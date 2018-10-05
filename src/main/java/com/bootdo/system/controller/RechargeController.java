package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.RechargeDO;
import com.bootdo.system.service.RechargeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
  R save(Integer number) {
    Long userId = getUserId();
    Map query = new HashMap();
    query.put("userId", userId);
    List<RechargeDO> list = rechargeService.list(query);
    if (rechargeService.save(getRechargeDO(userId, number, list)) > 0) {
      return R.ok();
    }

    return R.error();
  }

  private RechargeDO getRechargeDO(Long userId, Integer number, List<RechargeDO> list) {
    RechargeDO recharge = new RechargeDO();
    recharge.setUserId(userId);
    recharge.setType(getType(number));
    //第一次充值
    if (list == null || list.isEmpty()) {
      recharge.setBeginTime(new Date());
      recharge.setGmtCreate(new Date());
      recharge.setEndTime(getDate(new Date(), number));
      //会员20元/月
      recharge.setAmount(new Long(20 * number));
      recharge.setUserId(userId);
    } else {
      List<RechargeDO> sortedList = list.stream()
        .sorted(Comparator.comparing(RechargeDO::getEndTime).reversed()).collect(
          Collectors.toList());
      Date date = sortedList.get(0).getEndTime();
      recharge.setBeginTime(date);
      recharge.setGmtCreate(date);
      recharge.setEndTime(getDate(date, number));
      recharge.setAmount(new Long(20 * number));
      recharge.setUserId(userId);
    }
    return recharge;
  }

  private String getType(Integer number) {
    String type = "";
    if (1 == number) {
      type = "one";
    } else if (3 == number) {
      type = "three";
    } else if (6 == number) {
      type = "six";
    } else {
      type = "twelve";
    }
    return type;
  }

  private Date getDate(Date source, Integer number) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.MONTH, number);
    return calendar.getTime();
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
    params.put("userId", getUser().getUserId());
    Query query = new Query(params);
    List<RechargeDO> rechargeDOList = rechargeService.list(query);
    int total = rechargeService.count(query);
    PageUtils pageUtil = new PageUtils(rechargeDOList, total);
    return pageUtil;
  }
}
