package com.java.teaching.service;

import com.java.teaching.common.vo.CourseVo;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 * 课程管理服务类
 */
public interface CourseService {

    /**
     * 加载全部课程
     * @param courseVo
     * @return
     */
    DataGridView loadAllCourse(CourseVo courseVo);

    /**
     * 添加课程
     * @param courseVo
     */
    Integer addCourse(CourseVo courseVo);

    /**
     * 删除课程
     * @param id
     */
    void deleteCourse(Integer id);

    /**
     * 批量删除课程
     * @param courseVo
     */
    void batchDeleteCourse(CourseVo courseVo);

    /**
     * 修改课程信息
     * @param courseVo
     */
    void updateCourse(CourseVo courseVo);

    /**
     * 模糊查询课程
     * @param courseVo
     * @return
     */
    DataGridView fuzzyQueryCourse(CourseVo courseVo);

    /**
     * 根据分类id查询课程
     * @param courseVo
     * @return
     */
    DataGridView loadCourseBySortId(CourseVo courseVo);

    /**
     * 根据用户id加载课程
     * @param userVo
     * @return
     */
    DataGridView loadCourseByUserId(UserVo userVo);

    /**
     * 根据老师id查询该老师的教授课程
     * @param userVo
     * @return
     */
    DataGridView loadCourseByTeacherId(UserVo userVo);

    /**
     * 模糊查询全部课程
     * @param courseVo
     * @return
     */
    DataGridView fuzzyQueryAllCourse(CourseVo courseVo);
}
