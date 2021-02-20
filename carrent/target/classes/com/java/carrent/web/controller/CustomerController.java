package com.java.carrent.web.controller;

import com.java.carrent.common.vo.CustomerVo;
import com.java.carrent.entity.BusCustomer;
import com.java.carrent.service.CustomerService;
import com.java.carrent.utils.AppFileUtils;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.ImportDataUtils;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 客户管理控制器
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 加载全部客户信息
     * @param customerVo
     * @return
     */
    @GetMapping(value = "/loadAllCustomer.action")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {
        return this.customerService.queryAllCustomer(customerVo);
    }

    /**
     * 修改客户
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/updateCustomer.action")
    public ResultObj updateCustomer(CustomerVo customerVo) {
        try {
            this.customerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加客户
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/addCustomer.action")
    public ResultObj addCustomer(CustomerVo customerVo) {
        try {
            customerVo.setCreatetime(new Date());
            this.customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据identity删除客户
     * @param identity
     * @return
     */
    @PostMapping(value = "/deleteCustomer.action")
    public ResultObj deleteCustomer(String identity) {
        try {
            this.customerService.deleteCustomer(identity);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/batchDeleteCustomer.action")
    public ResultObj batchDeleteCustomer(CustomerVo customerVo) {
        try {
            this.customerService.batchDeleteCustomer(customerVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量添加用户
     * @param path
     * @return
     */
    @PostMapping(value = "/batchAddCustomer.action")
    public ResultObj batchAddCustomer(String path) {
//            1.读取excel文件
        System.out.println(path);
        try {
            List<BusCustomer> customerList = ImportDataUtils.importCustomerData(path);
            //        4.批量插入数据
            this.customerService.batchAddCustomer(customerList);
//            数据添加完毕，删除上传的excel文件
            AppFileUtils.deleteFile(path);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询全部黑名单用户
     * @param customerVo
     * @return
     */
    @GetMapping(value = "/queryAllBlacklistUser.action")
    public DataGridView queryAllBlacklistUser(CustomerVo customerVo) {

       return this.customerService.queryAllBlacklistUser(customerVo);
    }

    /**
     * 批量将用户移出黑名单
     * @param ids
     * @return
     */
    @PostMapping(value = "/batchMoveOut.action")
    public ResultObj batchMoveOut(String[] ids) {
        CustomerVo customerVo = null;
        try {
            for (String id : ids) {
                customerVo = new CustomerVo();
//                设置身份证信息
                customerVo.setIdentity(id);
//                调用移出黑名单的方法
                this.deleteBlacklist(customerVo);
            }
            return ResultObj.DELETE_BLACKLIST_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_BLACKLIST_ERROR;
        }

    }

    /**
     * 将用户添加到黑名单
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/addBlacklist.action")
    public ResultObj addBlacklist(CustomerVo customerVo) {
        try {
//            将用户的blacklist设置成1，表示是黑名单用户，0表示不是
            customerVo.setBlacklist("1");
            this.customerService.updateCustomer(customerVo);
            return ResultObj.ADD_TO_BLACKLIST_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_TO_BLACKLIST_ERROR;
        }
    }

    /**
     * 移出黑名单
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/deleteBlacklist.action")
    public ResultObj deleteBlacklist(CustomerVo customerVo) {
        try {
//            将用户黑名单状态设置成0
            customerVo.setBlacklist("0");
            this.customerService.updateCustomer(customerVo);
            return ResultObj.DELETE_BLACKLIST_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_BLACKLIST_ERROR;
        }
    }
}
