package com.java.carrent.mapper;

import com.java.carrent.common.vo.CheckVo;
import com.java.carrent.entity.BusCheck;
import com.java.carrent.entity.BusCheckExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCheckMapper {
    int countByExample(BusCheckExample example);

    int deleteByExample(BusCheckExample example);

    int deleteByPrimaryKey(String checkid);

    int insert(BusCheck record);

    int insertSelective(BusCheck record);

    List<BusCheck> selectByExample(BusCheckExample example);

    BusCheck selectByPrimaryKey(String checkid);

    int updateByExampleSelective(@Param("record") BusCheck record, @Param("example") BusCheckExample example);

    int updateByExample(@Param("record") BusCheck record, @Param("example") BusCheckExample example);

    int updateByPrimaryKeySelective(BusCheck record);

    int updateByPrimaryKey(BusCheck record);

    /**
     * 模糊查询检察单信息
     * @param checkVo
     * @return
     */
    List<BusCheck> fuzzyQueryCheck(CheckVo checkVo);
}