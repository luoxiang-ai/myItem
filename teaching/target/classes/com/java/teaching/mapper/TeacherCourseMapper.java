package com.java.teaching.mapper;

import com.java.teaching.entity.TeacherCourseExample;
import com.java.teaching.entity.TeacherCourseKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherCourseMapper {
    int countByExample(TeacherCourseExample example);

    int deleteByExample(TeacherCourseExample example);

    int deleteByPrimaryKey(TeacherCourseKey key);

    int insert(TeacherCourseKey record);

    int insertSelective(TeacherCourseKey record);

    List<TeacherCourseKey> selectByExample(TeacherCourseExample example);

    int updateByExampleSelective(@Param("record") TeacherCourseKey record, @Param("example") TeacherCourseExample example);

    int updateByExample(@Param("record") TeacherCourseKey record, @Param("example") TeacherCourseExample example);
}