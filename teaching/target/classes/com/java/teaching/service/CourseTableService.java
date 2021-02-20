package com.java.teaching.service;

import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.common.vo.CourseTableVo;
import com.java.teaching.utils.DataGridView;

/**
 * 课程表服务类
 */
public interface CourseTableService {

    /**
     * 添加课程表
     * @param courseTableVo
     */
    void addCourseTable(CourseTableVo courseTableVo);

    /**
     * 根据课程id查询课程表
     * @param courseTableVo
     * @return
     */
    DataGridView loadCourseTableByCid(CourseTableVo courseTableVo);
}
