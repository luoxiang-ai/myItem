package com.java.carrent.web.controller;

import com.java.carrent.common.vo.RentVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.BusCustomer;
import com.java.carrent.entity.SysUser;
import com.java.carrent.service.CustomerService;
import com.java.carrent.service.RentService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.RandomUtils;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 出租管理控制器
 */
@RestController
@RequestMapping(value = "/rent")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private CustomerService customerService;

    /**
     * 加载全部出租信息
     * @param rentVo
     * @return
     */
    @GetMapping(value = "/loadAllRent.action")
    public DataGridView loadAllRent(RentVo rentVo) {
        return this.rentService.queryAllRent(rentVo);
    }

    /**
     * 检查身份证是否存在
     * @param identity
     * @return
     */
    @PostMapping(value = "/checkCustomerExist.action")
    public ResultObj checkCustomerExist(String identity) {
        BusCustomer customer = this.customerService.queryCustomerByIdentity(identity);
        if(customer != null) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化出租表单
     * @param rentVo
     * @return
     */
    @GetMapping(value = "/initRentForm.action")
    public RentVo initRentForm(RentVo rentVo, HttpSession session) {
//        生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_RENT));
//        设置起租时间
        rentVo.setBegindate(new Date());
//        设置操作员
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        rentVo.setOpername(sysUser.getLoginname());

        return rentVo;
    }

    /**
     * 保存出租单
     * @param rentVo
     * @return
     */
    @PostMapping(value = "/saveRent.action")
    public ResultObj saveRent(RentVo rentVo) {
        try {
//            设置汽车的归还状态（0未归还）
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
            rentVo.setCreatetime(new Date());
            this.rentService.addRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改出租单信息
     * @param rentVo
     * @return
     */
    @PostMapping(value = "/updateRent.action")
    public ResultObj updateRent(RentVo rentVo) {
        try {
            this.rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据rentid删除出租单
     * @param rentId
     * @return
     */
    @PostMapping(value = "/deleteRent.action")
    public ResultObj deleteRent(String rentId) {
        try {
            this.rentService.deleteRent(rentId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询出租单信息
     * @param rentVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryRent.action")
    public DataGridView fuzzyQueryRent(RentVo rentVo) {
        return this.rentService.fuzzyQueryRent(rentVo);
    }
}
