package com.java.carrent.service;

import com.java.carrent.common.vo.LeaveBillVo;
import com.java.carrent.entity.SysLeaveBill;

import java.util.List;

/**
 *
 */
public interface LeaveBillService {

    /**
     * 查询全部请假单（超级管理员权限）
     * @param leaveBillVo
     * @return
     */
    List<SysLeaveBill> queryAllLeaveBill(LeaveBillVo leaveBillVo);

    /**
     * 添加请假单
     * @param leaveBillVo
     */
    void addLeaveBill(LeaveBillVo leaveBillVo);

    /**
     * 删除请假单
     * @param id
     */
    void deleteLeaveBill(Integer id);

    /**
     * 修改请假单信息
     * @param leaveBillVo
     */
    void updateLeaveBill(LeaveBillVo leaveBillVo);
}
