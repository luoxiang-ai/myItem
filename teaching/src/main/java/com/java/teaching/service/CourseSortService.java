package com.java.teaching.service;

import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 * 课程分类服务类
 */
public interface CourseSortService {

    /**
     * 加载全部课程分类
     * @param courseSortVo
     * @return
     */
    DataGridView loadAllCourseSort(CourseSortVo courseSortVo);

    /**
     * 添加课程分类
     * @param courseSortVo
     */
    void addCourseSort(CourseSortVo courseSortVo);

    /**
     * 删除课程分类
     * @param id
     */
    void deleteCourseSort(Integer id);

    /**
     * 批量删除课程分类
     * @param courseSortVo
     */
    void batchDeleteCourseSort(CourseSortVo courseSortVo);

    /**
     * 修改课程分类信息
     * @param courseSortVo
     */
    void updateCourseSort(CourseSortVo courseSortVo);

    /**
     * 模糊查询课程分类
     * @param courseSortVo
     * @return
     */
    DataGridView fuzzyQueryCourseSort(CourseSortVo courseSortVo);
}
