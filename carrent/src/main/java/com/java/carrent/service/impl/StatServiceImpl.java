package com.java.carrent.service.impl;

import com.java.carrent.entity.BaseEntity;
import com.java.carrent.mapper.StatMapper;
import com.java.carrent.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 统计分析的服务接口
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper statMapper;

    /**
     * 查询客户地区统计
     *
     * @return
     */
    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        List<BaseEntity> baseEntities = this.statMapper.queryCustomerAreaStat();
        for (BaseEntity baseEntity : baseEntities) {
            String[] contents = baseEntity.getName().split("/");
            System.out.println(Arrays.toString(contents));
        }
        return this.statMapper.queryCustomerAreaStat();
    }

    /**
     * 查询客户年度统计数据
     *
     * @param year
     * @return
     */
    @Override
    public List<BaseEntity> loadOpernameYearGradeStatList(String year) {

        return this.statMapper.queryOpernameYearGradeStat(year);
    }

    /**
     * 查询公司年度统计数据
     *
     * @param year
     * @return
     */
    @Override
    public List<Double> loadCompanyYearGradeStatList(String year) {

        return this.statMapper.queryCompanyYearGradeStat(year);
    }
}
