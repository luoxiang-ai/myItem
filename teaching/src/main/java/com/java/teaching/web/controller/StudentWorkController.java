package com.java.teaching.web.controller;

import com.java.teaching.common.vo.StudentWorkVo;
import com.java.teaching.service.StudentWorkService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生作业控制器
 */
@RestController
@RequestMapping(value = "/studentWork")
public class StudentWorkController {

    @Autowired
    private StudentWorkService studentWorkService;

    /**
     * 添加学生作业
     * @param studentWorkVo
     * @return
     */
    @PostMapping(value = "/addStudentWork.action")
    public ResultObj addStudentWork(StudentWorkVo studentWorkVo) {
        try {
            this.studentWorkService.addStudentWork(studentWorkVo);
            return ResultObj.ADD_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据作业布置id查询作业提交情况
     * @param studentWorkVo
     * @return
     */
    @GetMapping(value = "/loadStudentWorkByWorkId.action")
    public DataGridView loadStudentWorkByWorkId(StudentWorkVo studentWorkVo) {
        return this.studentWorkService.loadStudentWorkByWorkId(studentWorkVo);
    }

    /**
     * 批阅作业
     * @return
     */
    @PostMapping(value = "/reviewWork.action")
    public ResultObj reviewWork(Integer id) {
        try {
            this.studentWorkService.reviewWork(id);
            return ResultObj.REVIEW_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.REVIEW_ERROR;
        }
    }

    /**
     * 修改学生作业信息
     * @param studentWorkVo
     * @return
     */
    @PostMapping(value = "/updateStudentWork.action")
    public ResultObj updateStudentWork(StudentWorkVo studentWorkVo) {
        try {
            this.studentWorkService.updateStudentWork(studentWorkVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据用户id和作业id查询我提交的作业
     * @param studentWorkVo
     * @return
     */
    @GetMapping(value = "/loadMyWork.action")
    public DataGridView loadMyWork(StudentWorkVo studentWorkVo) {
        return this.studentWorkService.loadMyWork(studentWorkVo);
    }
}
