package com.java.teaching.mapper;

import com.java.teaching.entity.SysSuggest;
import com.java.teaching.entity.SysSuggestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSuggestMapper {
    int countByExample(SysSuggestExample example);

    int deleteByExample(SysSuggestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysSuggest record);

    int insertSelective(SysSuggest record);

    List<SysSuggest> selectByExample(SysSuggestExample example);

    SysSuggest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysSuggest record, @Param("example") SysSuggestExample example);

    int updateByExample(@Param("record") SysSuggest record, @Param("example") SysSuggestExample example);

    int updateByPrimaryKeySelective(SysSuggest record);

    int updateByPrimaryKey(SysSuggest record);
}