package com.java.carrent.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
     * 跳转到首页面
     * @return
     */
    @GetMapping(value = "/index.action")
    public String index() {

        return "system/main/index";
    }

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

    /**
     * 跳转到角色管理页面
     * @return
     */
    @GetMapping(value = "/toRoleManager.action")
    @RequiresPermissions(value = "sys:role")
    public String toRoleManager() {

        return "system/role/roleManager";
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @GetMapping(value = "/toUserManager.action")
    @RequiresPermissions(value = "sys:user")
    public String toUserManager() {

        return "system/user/userManager";
    }

    /**
     * 跳转到日志管理页面
     * @return
     */
    @GetMapping(value = "/toLogInfoManager.action")
    @RequiresPermissions(value = "sys:log")
    public String toLogInfoManager() {

        return "system/logInfo/logInfoManager";
    }

    /**
     * 跳转到公告管理页面
     * @return
     */
    @GetMapping(value = "/toNewsManager.action")
    @RequiresPermissions(value = "sys:news")
    public String toNewsManager() {

        return "system/news/newsManager";
    }

    /**
     * 跳转到个人资料管理页面
     * @return
     */
    @GetMapping(value = "/toPersonalManager.action")
    public String toPersonalManager() {

        return "system/user/personalManager";
    }

    /**
     * 跳转到工作台
     * @return
     */
    @GetMapping(value = "/toDeskManager.action")
    public String toDeskManager() {

        return "system/main/deskManager";
    }

    /**
     * 跳转到请假单管理页面
     * @return
     */
    @GetMapping(value = "/toLeaveBillManager.action")
    public String toLeaveBillManager() {

        return "system/leaveBill/leaveBillManager";
    }
}
