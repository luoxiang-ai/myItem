package com.java.teaching.web.controller;

import com.java.teaching.common.vo.TeacherCourseVo;
import com.java.teaching.entity.TeacherCourseKey;
import com.java.teaching.service.TeacherCourseService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教师课程关系控制器
 */
@RestController
@RequestMapping(value = "/teacherCourse")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    /**
     * 添加老师课程关系
     * @param teacherCourseVo
     * @return
     */
    @PostMapping(value = "/addTeacherCourse.action")
    public ResultObj addTeacherCourse(TeacherCourseVo teacherCourseVo) {
        try {
            this.teacherCourseService.addTeacherCourse(teacherCourseVo);
            return ResultObj.ADD_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 回显课程授课老师
     * @param teacherCourseVo
     * @return
     */
    @GetMapping(value = "/loadCourseTeacherList.action")
    public DataGridView loadCourseTeacherList(TeacherCourseVo teacherCourseVo) {
        return this.teacherCourseService.queryCourseTeacherList(teacherCourseVo);
    }

    /**
     * 更新课程授课老师
     * @param teacherCourseVo
     * @return
     */
    @PostMapping(value = "/updateCourseTeacher.action")
    public ResultObj updateCourseTeacher(TeacherCourseVo teacherCourseVo) {
        try {
            this.teacherCourseService.updateCourseTeacher(teacherCourseVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
