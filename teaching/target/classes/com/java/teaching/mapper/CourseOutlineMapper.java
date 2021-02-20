package com.java.teaching.mapper;

import com.java.teaching.entity.CourseOutline;
import com.java.teaching.entity.CourseOutlineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseOutlineMapper {
    int countByExample(CourseOutlineExample example);

    int deleteByExample(CourseOutlineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseOutline record);

    int insertSelective(CourseOutline record);

    List<CourseOutline> selectByExampleWithBLOBs(CourseOutlineExample example);

    List<CourseOutline> selectByExample(CourseOutlineExample example);

    CourseOutline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseOutline record, @Param("example") CourseOutlineExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseOutline record, @Param("example") CourseOutlineExample example);

    int updateByExample(@Param("record") CourseOutline record, @Param("example") CourseOutlineExample example);

    int updateByPrimaryKeySelective(CourseOutline record);

    int updateByPrimaryKeyWithBLOBs(CourseOutline record);

    int updateByPrimaryKey(CourseOutline record);
}