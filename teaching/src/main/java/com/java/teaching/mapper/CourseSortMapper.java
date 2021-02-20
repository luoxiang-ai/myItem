package com.java.teaching.mapper;

import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.entity.CourseSort;
import com.java.teaching.entity.CourseSortExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseSortMapper {
    int countByExample(CourseSortExample example);

    int deleteByExample(CourseSortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseSort record);

    int insertSelective(CourseSort record);

    List<CourseSort> selectByExample(CourseSortExample example);

    CourseSort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseSort record, @Param("example") CourseSortExample example);

    int updateByExample(@Param("record") CourseSort record, @Param("example") CourseSortExample example);

    int updateByPrimaryKeySelective(CourseSort record);

    int updateByPrimaryKey(CourseSort record);

    /**
     * 模糊查询课程分类
     * @param courseSortVo
     * @return
     */
    List<CourseSort> fuzzyQueryCourseSort(CourseSortVo courseSortVo);
}