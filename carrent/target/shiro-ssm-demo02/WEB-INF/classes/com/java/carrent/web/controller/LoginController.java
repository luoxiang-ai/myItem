package com.java.carrent.web.controller;

import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.SysUser;
import com.java.carrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login.action")
    public String login() {

        return "system/main/login";
    }

    @PostMapping(value = "/login.action")
    public String login(String username, String password, HttpSession session, HttpServletRequest request) {
        System.out.println(username + "===" + password);
        SysUser sysUser = this.userService.queryUserByUsername(username);
        if (sysUser != null) {
            session.setAttribute("sysUser", sysUser);
            return "system/main/index";
        }

        request.setAttribute("msg", SysConstast.LOGIN_ERROR_MSG);
//        return "forward:/login/login.action";
        return "system/main/login";
    }

}
