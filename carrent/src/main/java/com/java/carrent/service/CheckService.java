package com.java.carrent.service;

import com.java.carrent.common.vo.CheckVo;
import com.java.carrent.utils.DataGridView;

import java.util.Map;

/**
 * 检查单的服务接口
 */
public interface CheckService {

    /**
     * 根据出租单号加载检查单的表单数据
     * @param rentId
     * @return
     */
    public abstract Map<String, Object> initCheckFormData(String rentId, String loginName);

    /**
     * 保存检查单数据
     * @param checkVo
     */
    public abstract void addCheck(CheckVo checkVo);

    /**
     * 查询全部检查单
     * @param checkVo
     * @return
     */
    public abstract DataGridView queryAllCheck(CheckVo checkVo);

    /**
     * 修改检查单
     * @param checkVo
     * @return
     */
    public abstract void updateCheck(CheckVo checkVo);

    /**
     * 模糊查询检查单信息
     * @param checkVo
     * @return
     */
    DataGridView fuzzyQueryCheck(CheckVo checkVo);
}
