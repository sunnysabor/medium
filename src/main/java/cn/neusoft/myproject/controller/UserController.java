package cn.neusoft.myproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.neusoft.myproject.aspect.Log;
import cn.neusoft.myproject.domain.UserDO;
import cn.neusoft.myproject.service.UserService;
import cn.neusoft.myproject.utils.MD5Utils;
import cn.neusoft.myproject.utils.PageUtils;
import cn.neusoft.myproject.utils.Query;
import cn.neusoft.myproject.utils.R;
import cn.neusoft.myproject.vo.UserVO;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
  //定义用户的service
  @Autowired
  UserService userService;

  private String prefix = "system/user";

  //用于跳转路由
  @GetMapping("")
  String user(Model model) {
    return prefix + "/user";
  }

  //查询用户列表
  @GetMapping("/list")
  @ResponseBody
  PageUtils list(@RequestParam Map<String, Object> params) {
    // 查询列表数据
    Query query = new Query(params);
    List<UserDO> sysUserList = userService.list(query);
    int total = userService.count(query);
    PageUtils pageUtil = new PageUtils(sysUserList, total);
    return pageUtil;
  }

  //用于跳转到添加用户的路由
  @Log("添加用户")
  @GetMapping("/add")
  String add(Model model) {
    return prefix + "/add";
  }

  //用于跳转到编辑的路由
  @Log("编辑用户")
  @GetMapping("/edit/{id}")
  String edit(Model model, @PathVariable("id") Long id) {
    UserDO userDO = userService.get(id);
    model.addAttribute("user", userDO);
    return prefix + "/edit";
  }

  //保存用户信息
  @Log("保存用户")
  @PostMapping("/save")
  @ResponseBody
  R save(UserDO user) {
    user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));//用户的密码采用加盐加密
    user.setIdentity("user");
    if (userService.save(user) > 0) {
      return R.ok();
    }
    return R.error();
  }

  //更新用户信息
  @Log("更新用户")
  @PostMapping("/update")
  @ResponseBody
  R update(UserDO user) {
    if (userService.update(user) > 0) {
      return R.ok();
    }
    return R.error();
  }

  //更新用户是否启用
  @Log("更新用户")
  @PostMapping("/updatePeronal")
  @ResponseBody
  R updatePeronal(UserDO user) {
    if (userService.updatePersonal(user) > 0) {
      return R.ok();
    }
    return R.error();
  }

  //删除用户信息
  @Log("删除用户")
  @PostMapping("/remove")
  @ResponseBody
  R remove(Long id) {
    if (userService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  //批量删除用户信息
  @Log("批量删除用户")
  @PostMapping("/batchRemove")
  @ResponseBody
  R batchRemove(@RequestParam("ids[]") Long[] userIds) {
    int r = userService.batchremove(userIds);
    if (r > 0) {
      return R.ok();
    }
    return R.error();
  }

  //判断用户名是否存在
  @PostMapping("/exit")
  @ResponseBody
  boolean exit(@RequestParam Map<String, Object> params) {
    // 存在，不通过，false
    return !userService.exit(params);
  }

  //跳转修改密码的路由
  @Log("请求更改用户密码")
  @GetMapping("/resetPwd/{id}")
  String resetPwd(@PathVariable("id") Long userId, Model model) {
    UserDO userDO = new UserDO();
    userDO.setUserId(userId);
    model.addAttribute("user", userDO);
    return prefix + "/reset_pwd";
  }

  //用户修改密码
  @Log("提交更改用户密码")
  @PostMapping("/resetPwd")
  @ResponseBody
  R resetPwd(UserVO userVO) {
    try {
      userService.resetPwd(userVO, getUser());
      return R.ok();
    } catch (Exception e) {
      return R.error(1, e.getMessage());
    }
  }

  //管理修改用户密码
  @Log("admin提交更改用户密码")
  @PostMapping("/adminResetPwd")
  @ResponseBody
  R adminResetPwd(UserVO userVO) {
    try {
      userService.adminResetPwd(userVO);
      return R.ok();
    } catch (Exception e) {
      return R.error(1, e.getMessage());
    }
  }

  //跳转到用户树形结构页面
  @GetMapping("/treeView")
  String treeView() {
    return prefix + "/userTree";
  }

  //跳转到用户详情页面
  @GetMapping("/personal")
  String personal(Model model) {
    UserDO userDO = userService.get(getUserId());
    model.addAttribute("user", userDO);
    return prefix + "/personal";
  }

  //上传用户的头像
  @ResponseBody
  @PostMapping("/uploadImg")
  R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data,
    HttpServletRequest request) {
    Map<String, Object> result = new HashMap<>();
    try {
      result = userService.updatePersonalImg(file, avatar_data, getUserId());
    } catch (Exception e) {
      return R.error("更新图像失败！");
    }
    if (result != null && result.size() > 0) {
      return R.ok(result);
    } else {
      return R.error("更新图像失败！");
    }
  }
}
