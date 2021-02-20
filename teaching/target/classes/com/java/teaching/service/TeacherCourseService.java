package com.java.teaching.service;

import com.java.teaching.common.vo.TeacherCourseVo;
import com.java.teaching.entity.TeacherCourseKey;
import com.java.teaching.utils.DataGridView;

/**
 * 教师课程关系服务类
 */
public interface TeacherCourseService {

    /**
     * 添加老师课程关系
     * @param teacherCourseVo
     */
    void addTeacherCourse(TeacherCourseVo teacherCourseVo);

    /**
     * 回显课程授课老师
     * @param teacherCourseVo
     * @return
     */
    DataGridView queryCourseTeacherList(TeacherCourseVo teacherCourseVo);

    /**
     * 更新课程授课老师
     * @param teacherCourseVo
     */
    void updateCourseTeacher(TeacherCourseVo teacherCourseVo);
}
