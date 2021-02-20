package com.java.carrent.service;

import com.java.carrent.common.vo.LogInfoVo;
import com.java.carrent.utils.DataGridView;

public interface LogInfoService {

    /**
     * 查询全部日志信息
     * @param logInfoVo
     * @return
     */
    public abstract DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    /**
     * 添加日志
     * @param logInfoVo
     */
    public abstract void addLogInfo(LogInfoVo logInfoVo);

    /**
     * 根据id删除日志
     * @param logInfoid
     */
    public abstract void deleteLogInfo(Integer logInfoid);

    /**
     * 批量删除日志
     * @param ids
     */
    public void batchDeleteLogInfo(Integer[] ids);
}
