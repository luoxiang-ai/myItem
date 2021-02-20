package com.java.teaching.web.controller;

import com.java.teaching.common.vo.CourseSortVo;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.CourseSortService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/courseSort")
public class CourseSortController {

    @Autowired
    private CourseSortService courseSortService;

    /**
     * 加载全部课程分类
     * @param courseSortVo
     * @return
     */
    @GetMapping(value = "/loadAllCourseSort.action")
    public DataGridView loadAllCourseSort(CourseSortVo courseSortVo) {
        return this.courseSortService.loadAllCourseSort(courseSortVo);
    }

    /**
     * 修改课程分类
     * @param courseSortVo
     * @return
     */
    @PostMapping(value = "/updateCourseSort.action")
    public ResultObj updateCourseSort(CourseSortVo courseSortVo) {
        try {
            this.courseSortService.updateCourseSort(courseSortVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加课程分类
     * @param courseSortVo
     * @return
     */
    @PostMapping(value = "/addCourseSort.action")
    public ResultObj addCourseSort(CourseSortVo courseSortVo, HttpSession session) {
        try {
            //            获取用户信息
            SysUser sysUser = (SysUser) session.getAttribute("sysUser");
            this.courseSortService.addCourseSort(courseSortVo);
            return ResultObj.ADD_SUCCESS;
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
    @PostMapping(value = "/deleteCourseSort.action")
    public ResultObj deleteCourseSort(Integer id) {
        try {
            this.courseSortService.deleteCourseSort(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询课程分类
     * @param courseSortVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryCourseSort.action")
    public DataGridView fuzzyQueryCourseSort(CourseSortVo courseSortVo) {
        return this.courseSortService.fuzzyQueryCourseSort(courseSortVo);
    }
}
