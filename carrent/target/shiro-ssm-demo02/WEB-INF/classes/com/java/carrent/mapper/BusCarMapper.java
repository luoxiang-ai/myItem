package com.java.carrent.mapper;

import com.java.carrent.entity.BusCar;
import com.java.carrent.entity.BusCarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCarMapper {
    int countByExample(BusCarExample example);

    int deleteByExample(BusCarExample example);

    int deleteByPrimaryKey(String carnumber);

    int insert(BusCar record);

    int insertSelective(BusCar record);

    List<BusCar> selectByExample(BusCarExample example);

    BusCar selectByPrimaryKey(String carnumber);

    int updateByExampleSelective(@Param("record") BusCar record, @Param("example") BusCarExample example);

    int updateByExample(@Param("record") BusCar record, @Param("example") BusCarExample example);

    int updateByPrimaryKeySelective(BusCar record);

    int updateByPrimaryKey(BusCar record);
}