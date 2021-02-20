package com.java.carrent.service;

import com.java.carrent.entity.BaseEntity;

import java.util.List;

public interface StatService {

    /**
     * 查询客户地区统计
     * @return
     */
    public abstract List<BaseEntity> loadCustomerAreaStatList();

    /**
     * 查询客户年度统计数据
     * @param year
     * @return
     */
    public abstract List<BaseEntity> loadOpernameYearGradeStatList(String year);

    /**
     * 查询公司年度统计数据
     * @param year
     * @return
     */
    public abstract List<Double> loadCompanyYearGradeStatList(String year);
}
