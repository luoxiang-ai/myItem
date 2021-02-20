package com.java.teaching.web.controller;

import com.java.teaching.common.vo.CourseVo;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.CourseService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 课程管理控制器
 */
@RestController
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 加载全部课程
     * @param courseVo
     * @return
     */
    @GetMapping(value = "/loadAllCourse.action")
    public DataGridView loadAllCourse(CourseVo courseVo) {
        return this.courseService.loadAllCourse(courseVo);
    }

    /**
     * 修改课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/updateCourse.action")
    public ResultObj updateCourse(CourseVo courseVo) {
        try {
            this.courseService.updateCourse(courseVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/addCourse.action")
    public ResultObj addCourse(CourseVo courseVo) {
        try {
            Integer i = this.courseService.addCourse(courseVo);
            return new ResultObj(courseVo.getId(), SysConstast.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据编号删除班级
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteCourse.action")
    public ResultObj deleteCourse(Integer id) {
        try {
            this.courseService.deleteCourse(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/batchDeleteCourse.action")
    public ResultObj batchDeleteCourse(CourseVo courseVo) {
        try {
            this.courseService.batchDeleteCourse(courseVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询课程信息
     * @param courseVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryCourse.action")
    public DataGridView fuzzyQueryCourse(CourseVo courseVo, HttpSession session) {
//        设置用户id
        SysUser user = (SysUser)session.getAttribute("sysUser");
        courseVo.setStudentId(user.getUserid());
        return this.courseService.fuzzyQueryCourse(courseVo);
    }

    /**
     * 根据分类id查询课程
     * @param courseVo
     * @return
     */
    @GetMapping(value = "/loadCourseBySortId.action")
    public DataGridView loadCourseBySortId(CourseVo courseVo) {
        return this.courseService.loadCourseBySortId(courseVo);
    }

    /**
     * 根据用户id加载课程
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadCourseByUserId.action")
    public DataGridView loadCourseByUserId(UserVo userVo) {
        return this.courseService.loadCourseByUserId(userVo);
    }

    /**
     * 根据老师id查询该老师的教授课程
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadCourseByTeacherId.action")
    public DataGridView loadCourseByTeacherId(UserVo userVo) {
        return this.courseService.loadCourseByTeacherId(userVo);
    }

    /**
     * 模糊查询全部课程
     * @param courseVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryAllCourse.action")
    public DataGridView fuzzyQueryAllCourse(CourseVo courseVo) {
        return this.courseService.fuzzyQueryAllCourse(courseVo);
    }
}
