package com.java.carrent.mapper;

import com.java.carrent.entity.BusRent;
import com.java.carrent.entity.BusRentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusRentMapper {
    int countByExample(BusRentExample example);

    int deleteByExample(BusRentExample example);

    int deleteByPrimaryKey(String rentid);

    int insert(BusRent record);

    int insertSelective(BusRent record);

    List<BusRent> selectByExample(BusRentExample example);

    BusRent selectByPrimaryKey(String rentid);

    int updateByExampleSelective(@Param("record") BusRent record, @Param("example") BusRentExample example);

    int updateByExample(@Param("record") BusRent record, @Param("example") BusRentExample example);

    int updateByPrimaryKeySelective(BusRent record);

    int updateByPrimaryKey(BusRent record);
}