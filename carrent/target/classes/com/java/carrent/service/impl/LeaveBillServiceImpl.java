package com.java.carrent.service.impl;

import com.java.carrent.common.vo.LeaveBillVo;
import com.java.carrent.entity.SysLeaveBill;
import com.java.carrent.entity.SysLeaveBillExample;
import com.java.carrent.mapper.SysLeaveBillMapper;
import com.java.carrent.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假单服务实现类
 */
@Service
public class LeaveBillServiceImpl implements LeaveBillService {

    @Autowired
    private SysLeaveBillMapper sysLeaveBillMapper;

    /**
     * 查询全部请假单（超级管理员权限）
     *
     * @param leaveBillVo
     * @return
     */
    @Override
    public List<SysLeaveBill> queryAllLeaveBill(LeaveBillVo leaveBillVo) {
        SysLeaveBillExample example = new SysLeaveBillExample();
        example.setOrderByClause("leavetime ASC");
        List<SysLeaveBill> leaveBillList = this.sysLeaveBillMapper.selectByExample(example);
        return leaveBillList;
    }

    /**
     * 添加请假单
     *
     * @param leaveBillVo
     */
    @Override
    public void addLeaveBill(LeaveBillVo leaveBillVo) {
        if(leaveBillVo != null) {
            int i = this.sysLeaveBillMapper.insertSelective(leaveBillVo);
            System.out.println(i);
        }
    }

    /**
     * 删除请假单
     *
     * @param id
     */
    @Override
    public void deleteLeaveBill(Integer id) {
        if(id != null && id > 0) {
            int i = this.sysLeaveBillMapper.deleteByPrimaryKey(id);
            System.out.println(i);
        }
    }

    /**
     * 修改请假单信息
     *
     * @param leaveBillVo
     */
    @Override
    public void updateLeaveBill(LeaveBillVo leaveBillVo) {
        if(leaveBillVo != null) {
            int i = this.sysLeaveBillMapper.updateByPrimaryKeySelective(leaveBillVo);
            System.out.println(i);
        }
    }
}
