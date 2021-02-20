package com.java.carrent.mapper;

import com.java.carrent.entity.SysLeaveBill;
import com.java.carrent.entity.SysLeaveBillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLeaveBillMapper {
    int countByExample(SysLeaveBillExample example);

    int deleteByExample(SysLeaveBillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysLeaveBill record);

    int insertSelective(SysLeaveBill record);

    List<SysLeaveBill> selectByExample(SysLeaveBillExample example);

    SysLeaveBill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysLeaveBill record, @Param("example") SysLeaveBillExample example);

    int updateByExample(@Param("record") SysLeaveBill record, @Param("example") SysLeaveBillExample example);

    int updateByPrimaryKeySelective(SysLeaveBill record);

    int updateByPrimaryKey(SysLeaveBill record);
}