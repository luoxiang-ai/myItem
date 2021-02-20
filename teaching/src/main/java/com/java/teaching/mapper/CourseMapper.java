package com.java.teaching.mapper;

import com.java.teaching.common.vo.CourseVo;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.entity.Course;
import com.java.teaching.entity.CourseExample;
import com.java.teaching.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExampleWithBLOBs(CourseExample example);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExampleWithBLOBs(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);

    /**
     * 根据分类id查询课程
     * @param courseVo
     * @return
     */
    List<Course> loadCourseBySortId(CourseVo courseVo);

    /**
     * 根据用户id加载课程
     * @param userVo
     * @return
     */
    List<Course> loadCourseByUserId(UserVo userVo);

    /**
     * 根据老师id查询该老师的教授课程
     * @param userVo
     * @return
     */
    List<SysUser> loadCourseByTeacherId(UserVo userVo);

    /**
     * 模糊查询课程
     * @param courseVo
     * @return
     */
    List<Course> fuzzyQueryCourse(CourseVo courseVo);

    /**
     * 模糊查询全部课程
     * @param courseVo
     * @return
     */
    List<Course> fuzzyQueryAllCourse(CourseVo courseVo);
}