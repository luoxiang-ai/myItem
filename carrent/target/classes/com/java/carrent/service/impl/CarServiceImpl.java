package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.common.vo.CarVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.BusCar;
import com.java.carrent.entity.BusCarExample;
import com.java.carrent.mapper.BusCarMapper;
import com.java.carrent.service.CarService;
import com.java.carrent.utils.AppFileUtils;
import com.java.carrent.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 汽车管理服务接口
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private BusCarMapper busCarMapper;

    /**
     * 查询全部汽车
     *
     * @param carVo
     * @return
     */
    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        if (carVo != null) {
            Page<Object> page = PageHelper.startPage(carVo.getPage(), carVo.getLimit());
            BusCarExample example = new BusCarExample();
            example.setOrderByClause("createtime DESC");
            List<BusCar> busCarList = this.busCarMapper.selectByExample(example);
            return new DataGridView(page.getTotal(), busCarList);
        }
        return null;
    }

    /**
     * 添加车辆
     *
     * @param carVo
     */
    @Override
    public void addCar(CarVo carVo) {
        this.busCarMapper.insertSelective(carVo);
    }

    /**
     * 修改车辆信息
     *
     * @param carVo
     */
    @Override
    public void updateCar(CarVo carVo) {
        this.busCarMapper.updateByPrimaryKeySelective(carVo);
    }

    /**
     * 根据汽车车牌号查询该汽车
     *
     * @param carNumber
     */
    @Override
    public BusCar queryCarByCarNumber(String carNumber) {
        return this.busCarMapper.selectByPrimaryKey(carNumber);
    }

    /**
     * 删除汽车信息
     *
     * @param carNumber
     */
    @Override
    public void deleteCar(String carNumber) {
//        先查询数据库中汽车图片信息
        BusCar car = this.busCarMapper.selectByPrimaryKey(carNumber);
        String carImg = car.getCarimg();
//        判断它是不是默认图片，如果不是，则删除
        if(!SysConstast.DEFAULT_CAR_IMG.equals(carImg)) {
            AppFileUtils.deleteFile(carImg);
        }
//        删除数据库中的数据
        this.busCarMapper.deleteByPrimaryKey(carNumber);
    }

    /**
     * 批量删除汽车信息
     *
     * @param carNumbers
     */
    @Override
    public void batchDeleteCar(String[] carNumbers) {
        for (String carNumber : carNumbers) {
            this.deleteCar(carNumber);
        }
    }

    /**
     * 模糊查询汽车信息
     *
     * @param carVo
     * @return
     */
    @Override
    public DataGridView fuzzyQueryCar(CarVo carVo) {
        Page<Object> page = PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        List<BusCar> carList = this.busCarMapper.fuzzyQueryCar(carVo);
        return new DataGridView(page.getTotal(), carList);
    }
}
