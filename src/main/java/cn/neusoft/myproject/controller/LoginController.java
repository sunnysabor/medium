package cn.neusoft.myproject.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.neusoft.myproject.annotation.Log;
import cn.neusoft.myproject.domain.FileDO;
import cn.neusoft.myproject.domain.MenuDO;
import cn.neusoft.myproject.domain.Tree;
import cn.neusoft.myproject.service.FileService;
import cn.neusoft.myproject.service.MenuService;
import cn.neusoft.myproject.utils.MD5Utils;
import cn.neusoft.myproject.utils.R;
import cn.neusoft.myproject.utils.ShiroUtils;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;

    @GetMapping({"/", ""})
    String welcome(Model model) {
        return "/guest/guest";
    }

    @Log("请求访问主页")
    @GetMapping({"/index"})
    String index(Model model) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        FileDO fileDO = fileService.get(getUser().getPicId());
        if ("admin".equals(getUser().getIdentity())) {
            //返回后台
            if (fileDO != null && fileDO.getUrl() != null) {
                if (fileService.isExist(fileDO.getUrl())) {
                    model.addAttribute("picUrl", fileDO.getUrl());
                } else {
                    model.addAttribute("picUrl", "/img/photo_s.jpg");
                }
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
            model.addAttribute("username", getUser().getUsername());
            return "index_v1";
        } else if ("user".equals(getUser().getIdentity())) {
            //返回前台
            model.addAttribute("username", getUser().getUsername());
            return "/user/index/main";
        } else {
            //do nothing
            return null;
        }
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    public static void main(String[] args) {
        String pwd = MD5Utils.encrypt("test", "test");
        System.out.println(pwd);
    }
}
