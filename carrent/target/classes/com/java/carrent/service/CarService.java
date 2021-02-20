package com.java.carrent.service;

import com.java.carrent.common.vo.CarVo;
import com.java.carrent.entity.BusCar;
import com.java.carrent.utils.DataGridView;

/**
 * 汽车管理服务接口
 */
public interface CarService {

    /**
     * 查询全部汽车
     * @param carVo
     * @return
     */
    public abstract DataGridView queryAllCar(CarVo carVo);

    /**
     * 添加车辆
     * @param carVo
     */
    public abstract void addCar(CarVo carVo);

    /**
     * 修改车辆信息
     * @param carVo
     */
    public abstract void updateCar(CarVo carVo);

    /**
     * 根据汽车车牌号查询该汽车
     * @param carNumber
     */
    public abstract BusCar queryCarByCarNumber(String carNumber);

    /**
     * 删除汽车信息
     * @param carNumber
     */
    public abstract void deleteCar(String carNumber);

    /**
     * 批量删除汽车信息
     * @param carNumbers
     */
    public abstract void batchDeleteCar(String[] carNumbers);

    /**
     * 模糊查询汽车信息
     * @param carVo
     * @return
     */
    DataGridView fuzzyQueryCar(CarVo carVo);
}
