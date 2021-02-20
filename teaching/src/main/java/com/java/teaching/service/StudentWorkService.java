package com.java.teaching.service;

import com.java.teaching.common.vo.StudentWorkVo;
import com.java.teaching.utils.DataGridView;

/**
 * 学生作业服务类
 */
public interface StudentWorkService {

    /**
     * 添加学生作业
     * @param studentWorkVo
     */
    void addStudentWork(StudentWorkVo studentWorkVo);

    /**
     * 根据作业布置id查询作业提交情况
     * @param studentWorkVo
     * @return
     */
    DataGridView loadStudentWorkByWorkId(StudentWorkVo studentWorkVo);

    /**
     * 批阅作业
     * @param id
     */
    void reviewWork(Integer id);

    /**
     * 修改学生作业信息
     * @param studentWorkVo
     */
    void updateStudentWork(StudentWorkVo studentWorkVo);

    /**
     * 根据用户id和作业id查询我提交的作业
     * @param studentWorkVo
     * @return
     */
    DataGridView loadMyWork(StudentWorkVo studentWorkVo);
}
