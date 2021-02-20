package com.java.carrent.mapper;

import com.java.carrent.entity.SysLogLogin;
import com.java.carrent.entity.SysLogLoginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogLoginMapper {
    int countByExample(SysLogLoginExample example);

    int deleteByExample(SysLogLoginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysLogLogin record);

    int insertSelective(SysLogLogin record);

    List<SysLogLogin> selectByExample(SysLogLoginExample example);

    SysLogLogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysLogLogin record, @Param("example") SysLogLoginExample example);

    int updateByExample(@Param("record") SysLogLogin record, @Param("example") SysLogLoginExample example);

    int updateByPrimaryKeySelective(SysLogLogin record);

    int updateByPrimaryKey(SysLogLogin record);
}