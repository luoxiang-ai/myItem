package com.java.carrent.web.controller;

import com.java.carrent.common.vo.CarVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.BusCar;
import com.java.carrent.service.CarService;
import com.java.carrent.utils.AppFileUtils;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 汽车管理控制器
 */
@RestController
@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 加载全部汽车
     * @param carVo
     * @return
     */
    @GetMapping(value = "/loadAllCar.action")
    public DataGridView loadAllCar(CarVo carVo) {
        return this.carService.queryAllCar(carVo);
    }

    /**
     * 添加车辆
     * @param carVo
     * @return
     */
    @PostMapping(value = "/addCar.action")
    public ResultObj addCar(CarVo carVo) {
        try {
            carVo.setCreatetime(new Date());
//            如果不是默认图片就去掉图片后缀的_temp
            if(!SysConstast.DEFAULT_CAR_IMG.equals(carVo.getCarimg())) {
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstast.DEFAULT_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据carnumber删除汽车
     * @param carNumber
     * @return
     */
    @PostMapping(value = "/deleteCar.action")
    public ResultObj deleteCar(@RequestParam(value = "carnumber") String carNumber) {
        try {
            this.carService.deleteCar(carNumber);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除汽车
     * @param carVo
     * @return
     */
    @PostMapping(value = "/batchDeleteCar.action")
    public ResultObj batchDeleteCar(CarVo carVo) {
        try {
            this.carService.batchDeleteCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改汽车
     * @param carVo
     * @return
     */
    @PostMapping(value = "/updateCar.action")
    public ResultObj updateCar(CarVo carVo) {
        try {
//            如果不是默认图片就去除_temp后缀
            String carImg = carVo.getCarimg();
            if(!SysConstast.DEFAULT_CAR_IMG.equals(carImg)) {
//                删除原先的图片
                BusCar car = this.carService.queryCarByCarNumber(carVo.getCarnumber());
                AppFileUtils.deleteFile(AppFileUtils.PATH + car.getCarimg());

                String filePath = AppFileUtils.updateFileName(carImg, SysConstast.DEFAULT_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.updateCar(carVo);

            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 模糊查询汽车信息
     * @param carVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryCar.action")
    public DataGridView fuzzyQueryCar(CarVo carVo) {
        return this.carService.fuzzyQueryCar(carVo);
    }
}
