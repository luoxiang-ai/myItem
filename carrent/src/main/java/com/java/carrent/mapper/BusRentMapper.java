package com.java.carrent.mapper;

import com.java.carrent.common.vo.RentVo;
import com.java.carrent.entity.BusRentExample;
import com.java.carrent.entity.BusRent;
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

    /**
     * 模糊查询出租单信息
     * @param rentVo
     * @return
     */
    List<BusRent> fuzzyQueryRent(RentVo rentVo);
}