package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.common.vo.LogInfoVo;
import com.java.carrent.entity.SysLogLogin;
import com.java.carrent.entity.SysLogLoginExample;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.mapper.SysLogLoginMapper;
import com.java.carrent.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志信息Service实现类
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private SysLogLoginMapper sysLogLoginMapper;

    /**
     * 查询全部日志信息
     *
     * @param logInfoVo
     * @return
     */
    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page = PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
        SysLogLoginExample sysLogLoginExample = new SysLogLoginExample();
        SysLogLoginExample.Criteria criteria = sysLogLoginExample.createCriteria();
//        排序问题
        sysLogLoginExample.setOrderByClause("logintime DESC");
        List<SysLogLogin> sysLogLoginList = this.sysLogLoginMapper.selectByExample(sysLogLoginExample);
        return new DataGridView(page.getTotal(), sysLogLoginList);
    }

    /**
     * 添加日志
     *
     * @param logInfoVo
     */
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        this.sysLogLoginMapper.insertSelective(logInfoVo);
    }

    /**
     * 根据id删除日志
     *
     * @param logInfoid
     */
    @Override
    public void deleteLogInfo(Integer logInfoid) {
        this.sysLogLoginMapper.deleteByPrimaryKey(logInfoid);
    }

    /**
     * 批量删除日志
     *
     * @param ids
     */
    @Override
    public void batchDeleteLogInfo(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteLogInfo(id);
        }
    }
}
