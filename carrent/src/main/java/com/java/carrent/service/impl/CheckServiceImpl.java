package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.common.vo.CheckVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.*;
import com.java.carrent.mapper.BusCarMapper;
import com.java.carrent.mapper.BusCheckMapper;
import com.java.carrent.mapper.BusCustomerMapper;
import com.java.carrent.mapper.BusRentMapper;
import com.java.carrent.service.CheckService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查汽车入库服务现实类
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private BusCheckMapper busCheckMapper;
    @Autowired
    private BusCustomerMapper busCustomerMapper;
    @Autowired
    private BusRentMapper busRentMapper;
    @Autowired
    private BusCarMapper busCarMapper;

    /**
     * 根据出租单号加载检查单的表单数据
     *
     * @param rentId
     * @return
     */
    @Override
    public Map<String, Object> initCheckFormData(String rentId, String loginName) {
//        查询出租单
        BusRent rent = this.busRentMapper.selectByPrimaryKey(rentId);
//         查询客户
        BusCustomer customer = this.busCustomerMapper.selectByPrimaryKey(rent.getIdentity());
//        客户车辆
        BusCar car = this.busCarMapper.selectByPrimaryKey(rent.getCarnumber());
//        组装check
        BusCheck check = new BusCheck();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CHECK));
        check.setRentid(rentId);
        check.setCheckdate(new Date());
        check.setOpername(loginName);

        Map<String, Object> map = new HashMap<>();
        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);

        return map;
    }

    /**
     * 保存检查单数据
     *
     * @param checkVo
     */
    @Override
    public void addCheck(CheckVo checkVo) {
        checkVo.setCreatetime(new Date());
        this.busCheckMapper.insertSelective(checkVo);
//        1.更改出租单的状态
        BusRent rent = this.busRentMapper.selectByPrimaryKey(checkVo.getRentid());
//        设置成归还状态
        rent.setRentflag(SysConstast.RENT_BACK_TRUE);
        this.busRentMapper.updateByPrimaryKeySelective(rent);
//        2.更改汽车的状态
        BusCar car = new BusCar();
        car.setCarnumber(rent.getCarnumber());
//        将汽车设置成未出租状态
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        this.busCarMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询全部检查单
     *
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        BusCheckExample example = new BusCheckExample();
        example.setOrderByClause("createtime DESC");
        List<BusCheck> checkList = this.busCheckMapper.selectByExample(example);
        return new DataGridView(page.getTotal(), checkList);
    }

    /**
     * 修改检查单
     *
     * @param checkVo
     * @return
     */
    @Override
    public void updateCheck(CheckVo checkVo) {
        this.busCheckMapper.updateByPrimaryKeySelective(checkVo);
    }

    /**
     * 模糊查询检查单信息
     *
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView fuzzyQueryCheck(CheckVo checkVo) {
        Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<BusCheck> checkList = this.busCheckMapper.fuzzyQueryCheck(checkVo);
        return new DataGridView(page.getTotal(), checkList);
    }
}
