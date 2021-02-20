package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.common.vo.RentVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.BusCar;
import com.java.carrent.entity.BusRent;
import com.java.carrent.entity.BusRentExample;
import com.java.carrent.mapper.BusCarMapper;
import com.java.carrent.mapper.BusRentMapper;
import com.java.carrent.service.RentService;
import com.java.carrent.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出租信息服务接口的实现类
 */
@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private BusRentMapper busRentMapper;
    @Autowired
    private BusCarMapper busCarMapper;

    /**
     * 保存出租信息
     *
     * @param rentVo
     */
    @Override
    public void addRent(RentVo rentVo) {
        this.busRentMapper.insertSelective(rentVo);
//        更改车辆的出租状态
        BusCar car = new BusCar();
        car.setCarnumber(rentVo.getCarnumber());
//        1是代表已出租， 0代表未出租
        car.setIsrenting(SysConstast.RENT_BACK_TRUE);
        this.busCarMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询所有出租信息
     *
     * @param rentVo
     * @return
     */
    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        BusRentExample busRentExample = new BusRentExample();
        busRentExample.setOrderByClause("begindate DESC");
        List<BusRent> busRentList = this.busRentMapper.selectByExample(busRentExample);
        return new DataGridView(page.getTotal(), busRentList);
    }

    /**
     * 删除出租信息
     *
     * @param rentId
     */
    @Override
    public void deleteRent(String rentId) {
//        先根据rentid查询该出租单的信息
        BusRent rent = this.busRentMapper.selectByPrimaryKey(rentId);
        BusCar car = new BusCar();
        car.setCarnumber(rent.getCarnumber());
//        更改汽车状态（0表示未出租，1表示已出租）
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        this.busCarMapper.updateByPrimaryKeySelective(car);

//        删除出租单信息
        this.busRentMapper.deleteByPrimaryKey(rentId);
    }

    /**
     * 修改出租信息
     *
     * @param rentVo
     */
    @Override
    public void updateRent(RentVo rentVo) {
        this.busRentMapper.updateByPrimaryKeySelective(rentVo);
    }

    /**
     * 根据出租单号查询出租单信息
     *
     * @param rentId
     * @return
     */
    @Override
    public BusRent queryRentByRentId(String rentId) {
        return this.busRentMapper.selectByPrimaryKey(rentId);
    }

    /**
     * 模糊查询出租单信息
     *
     * @param rentVo
     * @return
     */
    @Override
    public DataGridView fuzzyQueryRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        List<BusRent> rentList = this.busRentMapper.fuzzyQueryRent(rentVo);
        return new DataGridView(page.getTotal(), rentList);
    }
}
