package com.java.carrent.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bus")
public class BusController {

    /**
     * 跳转到客户管理页面
     * @return
     */
    @GetMapping(value = "/toCustomerManager.action")
    @RequiresPermissions(value = "bus:customer")
    public String toCustomerManager() {

        return "business/customer/customerManager";
    }

    /**
     * 跳转到汽车管理页面
     * @return
     */
    @GetMapping(value = "/toCarManager.action")
    @RequiresPermissions(value = "bus:car")
    public String toCarManager() {

        return "business/car/carManager";
    }

    /**
     * 跳转到汽车出租页面
     * @return
     */
    @GetMapping(value = "/toRentCarManager.action")
    @RequiresPermissions(value = "bus:rentCar")
    public String toRentCarManager() {

        return "business/rent/rentCarManager";
    }

    /**
     * 跳转到出租单管理页面
     * @return
     */
    @GetMapping(value = "/toRentManager.action")
    @RequiresPermissions(value = "bus:rent")
    public String toRentManager() {

        return "business/rent/rentManager";
    }

    /**
     * 跳转到汽车入库的页面
     * @return
     */
    @GetMapping(value = "/toCheckCarManager.action")
    @RequiresPermissions(value = "bus:checkCar")
    public String toCheckCarManager() {

        return "business/check/checkCarManager";
    }

    /**
     * 跳转到检查单管理页面
     * @return
     */
    @GetMapping(value = "/toCheckManager.action")
    @RequiresPermissions(value = "bus:check")
    public String toCheckManager() {

        return "business/check/checkManager";
    }

    /**
     * 跳转到多文件上传页面
     * @return
     */
    @GetMapping(value = "/toUploadManager.action")
    public String toUploadManager() {

        return "business/upload/upload";
    }

    /**
     * 跳转黑名单管理页面
     * @return
     */
    @GetMapping(value = "/toBlacklistManager.action")
    @RequiresPermissions(value = "bus:black")
    public String toBlacklistManager() {

        return "business/customer/blacklist";
    }
}
