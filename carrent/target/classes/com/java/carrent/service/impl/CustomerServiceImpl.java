package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.common.vo.CustomerVo;
import com.java.carrent.entity.BusCustomer;
import com.java.carrent.entity.BusCustomerExample;
import com.java.carrent.mapper.BusCustomerMapper;
import com.java.carrent.service.CustomerService;
import com.java.carrent.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 客户管理服务接口实现类
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BusCustomerMapper busCustomerMapper;

    /**
     * 查询所有客户
     *
     * @param customerVo
     * @return
     */
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        if (customerVo != null) {
            Page<Object> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
            List<BusCustomer> busCustomerList = this.busCustomerMapper.queryAllCustomer(customerVo);
            return new DataGridView(page.getTotal(), busCustomerList);
        }
        return null;
    }

    /**
     * 添加客户
     *
     * @param customerVo
     */
    @Override
    public void addCustomer(CustomerVo customerVo) {
        this.busCustomerMapper.insertSelective(customerVo);
    }

    /**
     * 修改客户
     *
     * @param customerVo
     */
    @Override
    public void updateCustomer(CustomerVo customerVo) {
        this.busCustomerMapper.updateByPrimaryKeySelective(customerVo);
    }

    /**
     * 删除客户
     *
     * @param identity
     */
    @Override
    public void deleteCustomer(String identity) {
        this.busCustomerMapper.deleteByPrimaryKey(identity);
    }

    /**
     * 批量删除客户
     *
     * @param customerVo
     */
    @Override
    public void batchDeleteCustomer(CustomerVo customerVo) {
        for (String identity : customerVo.getIds()) {
            this.deleteCustomer(identity);
        }
    }

    /**
     * 根据身份证号查询客户信息
     *
     * @param identity
     */
    @Override
    public BusCustomer queryCustomerByIdentity(String identity) {
        return this.busCustomerMapper.selectByPrimaryKey(identity);
    }

    /**
     * 查询全部客户信息，返回一个集合
     *
     * @param customerVo
     * @return
     */
    @Override
    public List<BusCustomer> queryAllCustomerForList(CustomerVo customerVo) {

        return this.busCustomerMapper.selectByExample(new BusCustomerExample());
    }

    /**
     * 批量添加用户
     *
     * @param customerList
     */
    @Override
    public void batchAddCustomer(List<BusCustomer> customerList) {
        for (BusCustomer customer : customerList) {
            customer.setCreatetime(new Date());
            this.busCustomerMapper.insertSelective(customer);
        }
    }

    /**
     * 查询全部黑名单客户
     *
     * @param customerVo
     * @return
     */
    @Override
    public DataGridView queryAllBlacklistUser(CustomerVo customerVo) {
        Page<Object> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
//                添加查询条件
        BusCustomerExample customerExample = new BusCustomerExample();
        BusCustomerExample.Criteria criteria = customerExample.createCriteria();
//        只要blacklist这个字段等于1,表示这是个黑用户
        criteria.andBlacklistEqualTo("1");
        List<BusCustomer> customerList = this.busCustomerMapper.selectByExample(customerExample);
        return new DataGridView(page.getTotal(), customerList);
    }

}
