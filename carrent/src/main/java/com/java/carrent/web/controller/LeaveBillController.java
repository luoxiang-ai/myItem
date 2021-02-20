package com.java.carrent.web.controller;

import com.java.carrent.common.vo.LeaveBillVo;
import com.java.carrent.entity.SysLeaveBill;
import com.java.carrent.service.LeaveBillService;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假单控制器
 */
@Controller
@RequestMapping(value = "/leaveBill")
public class LeaveBillController {

    @Autowired
    private LeaveBillService leaveBillService;

    /**
     * 加载全部请假单
     * @param leaveBillVo
     * @return
     */
    @GetMapping(value = "/loadAllLeaveBill.action")
    @ResponseBody
    public Map<String, Object> loadAllLeaveBill(LeaveBillVo leaveBillVo) {
        List<SysLeaveBill> leaveBillList = this.leaveBillService.queryAllLeaveBill(leaveBillVo);
        System.out.println(leaveBillList);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", leaveBillList.size());
        map.put("data", leaveBillList);
        return map;
    }

    @PostMapping(value = "/addLeaveBill.action")
    @ResponseBody
    public ResultObj addLeaveBill(LeaveBillVo leaveBillVo) {
        try {
            System.out.println("sb");
            this.leaveBillService.addLeaveBill(leaveBillVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    public ResultObj deleteLeaveBill(Integer id) {
        try {
            this.leaveBillService.deleteLeaveBill(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    public ResultObj updateLeaveBill(LeaveBillVo leaveBillVo) {
        try {
            this.leaveBillService.updateLeaveBill(leaveBillVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
