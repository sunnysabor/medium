package com.bootdo.system.controller;

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

import com.bootdo.system.annotation.Log;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import com.bootdo.system.utils.MD5Utils;
import com.bootdo.system.utils.PageUtils;
import com.bootdo.system.utils.Query;
import com.bootdo.system.utils.R;
import com.bootdo.system.vo.UserVO;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    private String prefix = "system/user";

    @GetMapping("")
    String user(Model model) {
        return prefix + "/user";
    }

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

    @Log("添加用户")
    @GetMapping("/add")
    String add(Model model) {
        return prefix + "/add";
    }

    @Log("编辑用户")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        UserDO userDO = userService.get(id);
        model.addAttribute("user", userDO);
        return prefix + "/edit";
    }

    @Log("保存用户")
    @PostMapping("/save")
    @ResponseBody
    R save(UserDO user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        user.setIdentity("user");
        if (userService.save(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("更新用户")
    @PostMapping("/update")
    @ResponseBody
    R update(UserDO user) {
        if (userService.update(user) > 0) {
            return R.ok();
        }
        return R.error();
    }


    @Log("更新用户")
    @PostMapping("/updatePeronal")
    @ResponseBody
    R updatePeronal(UserDO user) {
        if (userService.updatePersonal(user) > 0) {
            return R.ok();
        }
        return R.error();
    }


    @Log("删除用户")
    @PostMapping("/remove")
    @ResponseBody
    R remove(Long id) {
        if (userService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

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

    @PostMapping("/exit")
    @ResponseBody
    boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exit(params);
    }

    @Log("请求更改用户密码")
    @GetMapping("/resetPwd/{id}")
    String resetPwd(@PathVariable("id") Long userId, Model model) {

        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        model.addAttribute("user", userDO);
        return prefix + "/reset_pwd";
    }

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

    @GetMapping("/treeView")
    String treeView() {
        return prefix + "/userTree";
    }

    @GetMapping("/personal")
    String personal(Model model) {
        UserDO userDO = userService.get(getUserId());
        model.addAttribute("user", userDO);
        return prefix + "/personal";
    }

    @ResponseBody
    @PostMapping("/uploadImg")
    R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
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
