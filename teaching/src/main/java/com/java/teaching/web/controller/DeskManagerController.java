package com.java.teaching.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台
 */
@Controller
@RequestMapping(value = "/desk")
public class DeskManagerController {

    /**
     * 跳转到工作台页面
     * @return
     */
    @GetMapping(value = "/toDeskManager.action")
    public String toDeskManager() {

        return "system/main/deskManager";
    }
}
