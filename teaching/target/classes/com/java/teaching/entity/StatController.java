package com.java.teaching.entity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统计分析页面跳转控制器
 */
@Controller
@RequestMapping(value = "/stat")
public class StatController {

    /**
     * 跳转到学生地区统计页面
     * @return
     */
    @GetMapping(value = "/toStudentAreaStat.action")
    public String toStudentAreaStat() {

        return "stat/studentAreaStat";
    }
}
