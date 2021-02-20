package com.java.teaching.mapper;

import com.java.teaching.entity.CourseTable;
import com.java.teaching.entity.CourseTableExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTableMapper {
    int countByExample(CourseTableExample example);

    int deleteByExample(CourseTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTable record);

    int insertSelective(CourseTable record);

    List<CourseTable> selectByExample(CourseTableExample example);

    CourseTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseTable record, @Param("example") CourseTableExample example);

    int updateByExample(@Param("record") CourseTable record, @Param("example") CourseTableExample example);

    int updateByPrimaryKeySelective(CourseTable record);

    int updateByPrimaryKey(CourseTable record);
}