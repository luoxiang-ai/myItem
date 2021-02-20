package com.java.carrent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器--作用：路由
 */
@Controller
@RequestMapping("/sys")
public class SysController {

    /**
     * 跳转到菜单管理
     * @return
     */
    @GetMapping(value = "/toMenuManager.action")
    public String toMenuManager() {

        return "system/menu/menuManager";
    }

    /**
     * 跳转菜单管理左边的的菜单树页面
     * @return
     */
    @GetMapping(value = "/toMenuLeft.action")
    public String toMenuLeft() {

        return "system/menu/menuLeft";
    }

    /**
     * 跳转菜单管理右边的菜单列表
     * @return
     */
    @GetMapping(value = "/toMenuRight.action")
    public String toMenuRight() {

        return "system/menu/menuRight";
    }
}
