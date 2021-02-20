package com.java.teaching.web.controller;

import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.common.vo.CourseTableVo;
import com.java.teaching.service.CourseTableService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程表控制器
 */
@RestController
@RequestMapping(value = "/courseTable")
public class CourseTableController {

    @Autowired
    private CourseTableService courseTableService;

    /**
     * 添加课程表
     * @return
     */
    @PostMapping(value = "/addCourseTable.action")
    public ResultObj addCourseTable(CourseTableVo courseTableVo) {
        try {
            this.courseTableService.addCourseTable(courseTableVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据课程id查询课程表
     * @param courseTableVo
     * @return
     */
    @GetMapping(value = "/loadCourseTableByCid.action")
    public DataGridView loadCourseTableByCid(CourseTableVo courseTableVo) {
        return this.courseTableService.loadCourseTableByCid(courseTableVo);
    }
}
