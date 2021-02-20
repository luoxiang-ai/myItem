package com.java.carrent.service;

import com.java.carrent.common.vo.CustomerVo;
import com.java.carrent.entity.BusCustomer;
import com.java.carrent.utils.DataGridView;

import java.util.List;


/**
 * 客户管理服务接口
 */
public interface CustomerService {

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    public abstract DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     * 添加客户
     * @param customerVo
     */
    public abstract void addCustomer(CustomerVo customerVo);

    /**
     * 修改客户
     * @param customerVo
     */
    public abstract void updateCustomer(CustomerVo customerVo);

    /**
     * 根据身份证号删除客户
     * @param identity
     */
    public abstract void deleteCustomer(String identity);

    /**
     * 批量删除客户
     * @param customerVo
     */
    public abstract void batchDeleteCustomer(CustomerVo customerVo);

    /**
     * 根据身份证号查询客户信息
     * @param identity
     */
    public abstract BusCustomer queryCustomerByIdentity(String identity);

    /**
     * 查询全部客户信息，返回一个集合
     * @param customerVo
     * @return
     */
    public abstract List<BusCustomer> queryAllCustomerForList(CustomerVo customerVo);

    /**
     * 批量添加用户
     * @param customerList
     */
    public abstract void batchAddCustomer(List<BusCustomer> customerList);

    /**
     * 查询全部黑名单客户
     * @param customerVo
     * @return
     */
    public abstract DataGridView queryAllBlacklistUser(CustomerVo customerVo);
}
