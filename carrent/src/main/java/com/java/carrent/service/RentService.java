package com.java.carrent.service;

import com.java.carrent.common.vo.RentVo;
import com.java.carrent.entity.BusRent;
import com.java.carrent.utils.DataGridView;

/**
 * 汽车出租的服务接口
 */
public interface RentService {

    /**
     * 保存出租信息
     * @param rentVo
     */
    public abstract void addRent(RentVo rentVo);

    /**
     * 查询所有出租信息
     * @param rentVo
     * @return
     */
    public abstract DataGridView queryAllRent(RentVo rentVo);

    /**
     * 删除出租信息
     * @param rentId
     */
    public abstract void deleteRent(String rentId);

    /**
     * 修改出租信息
     * @param rentVo
     */
    public abstract void updateRent(RentVo rentVo);

    /**
     * 根据出租单号查询出租单信息
     * @param rentId
     * @return
     */
    public abstract BusRent queryRentByRentId(String rentId);

    /**
     * 模糊查询出租单信息
     * @param rentVo
     * @return
     */
    DataGridView fuzzyQueryRent(RentVo rentVo);
}
