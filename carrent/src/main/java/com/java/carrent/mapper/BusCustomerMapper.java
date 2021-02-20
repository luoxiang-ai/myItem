package com.java.carrent.mapper;

import com.java.carrent.common.vo.CustomerVo;
import com.java.carrent.entity.BusCustomer;
import com.java.carrent.entity.BusCustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCustomerMapper {
    int countByExample(BusCustomerExample example);

    int deleteByExample(BusCustomerExample example);

    int deleteByPrimaryKey(String identity);

    int insert(BusCustomer record);

    int insertSelective(BusCustomer record);

    List<BusCustomer> selectByExample(BusCustomerExample example);

    BusCustomer selectByPrimaryKey(String identity);

    int updateByExampleSelective(@Param("record") BusCustomer record, @Param("example") BusCustomerExample example);

    int updateByExample(@Param("record") BusCustomer record, @Param("example") BusCustomerExample example);

    int updateByPrimaryKeySelective(BusCustomer record);

    int updateByPrimaryKey(BusCustomer record);

    /**
     * 模糊查询用户
     * @param customerVo
     * @return
     */
    List<BusCustomer> queryAllCustomer(CustomerVo customerVo);
}