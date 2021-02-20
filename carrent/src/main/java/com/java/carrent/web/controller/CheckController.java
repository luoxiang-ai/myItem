package com.java.carrent.web.controller;

import com.java.carrent.common.vo.CheckVo;
import com.java.carrent.entity.BusRent;
import com.java.carrent.entity.SysUser;
import com.java.carrent.service.CheckService;
import com.java.carrent.service.RentService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 检查汽车入库的控制器
 */
@RestController
@RequestMapping(value = "/check")
public class CheckController {

    @Autowired
    private RentService rentService;
    @Autowired
    private CheckService checkService;

    /**
     * 检查出租单号是存在和是已经还车
     * @param rentId
     * @return
     */
    @PostMapping(value = "/checkRentExist.action")
    public BusRent checkRentExist(@RequestParam(value = "rentid") String rentId) {

        return this.rentService.queryRentByRentId(rentId);
    }

    /**
     * 初始化查检单数据
     * @param rentId
     * @return
     */
    @PostMapping(value = "/initCheckFormData.action")
    public Map<String, Object> initCheckFormData(String rentId, HttpSession session) {
        SysUser user = (SysUser)session.getAttribute("sysUser");
        return this.checkService.initCheckFormData(rentId, user.getLoginname());
    }

    /**
     * 确定汽车入库
     * @param checkVo
     */
    @PostMapping(value = "/addCheck.action")
    public ResultObj addCheck(CheckVo checkVo) {
        try {
            this.checkService.addCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载全部检查单
     * @param checkVo
     * @return
     */
    @GetMapping(value = "/loadAllCheck.action")
    public DataGridView loadAllCheck(CheckVo checkVo) {
        return this.checkService.queryAllCheck(checkVo);
    }

    /**
     * 修改检查单
     * @param checkVo
     * @return
     */
    @PostMapping(value = "/updateCheck.action")
    public ResultObj updateCheck(CheckVo checkVo) {
        try {
            this.checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 模糊查询检查单信息
     * @param checkVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryCheck.action")
    public DataGridView fuzzyQueryCheck(CheckVo checkVo) {
        return this.checkService.fuzzyQueryCheck(checkVo);
    }
}
