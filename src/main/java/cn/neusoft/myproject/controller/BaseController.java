package cn.neusoft.myproject.controller;

import org.springframework.stereotype.Controller;

import cn.neusoft.myproject.domain.UserDO;
import cn.neusoft.myproject.utils.ShiroUtils;

//基类，用于存储上下文的用户信息
@Controller
public class BaseController {
    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }
}