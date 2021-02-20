package com.java.carrent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试ajax
 */
@RestController
@RequestMapping(value = "/test")
public class AjaxTest {

    @GetMapping(value = "/login.action")
    public String login(String name) {
        System.out.println(name);
        return "";
    }
}
