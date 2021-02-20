package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.StudentWorkVo;
import com.java.teaching.entity.StudentWork;
import com.java.teaching.mapper.StudentWorkMapper;
import com.java.teaching.service.StudentWorkService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生作业服务实现类
 */
@Service
public class StudentWorkServiceImpl implements StudentWorkService {

    @Autowired
    private StudentWorkMapper studentWorkMapper;

    /**
     * 添加学生作业
     *
     * @param studentWorkVo
     */
    @Override
    public void addStudentWork(StudentWorkVo studentWorkVo) {
//        设置作业标识
        studentWorkVo.setFlag(false);
        this.studentWorkMapper.insertSelective(studentWorkVo);
    }

    /**
     * 根据作业布置id查询作业提交情况
     *
     * @param studentWorkVo
     * @return
     */
    @Override
    public DataGridView loadStudentWorkByWorkId(StudentWorkVo studentWorkVo) {
        Page<Object> page = PageHelper.startPage(studentWorkVo.getPage(), studentWorkVo.getLimit());
        List<StudentWorkVo> list = this.studentWorkMapper.loadStudentWorkByWorkId(studentWorkVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 批阅作业
     *
     * @param id
     */
    @Override
    public void reviewWork(Integer id) {
        StudentWorkVo studentWorkVo = new StudentWorkVo();
        studentWorkVo.setId(id);
        studentWorkVo.setFlag(true);
        this.studentWorkMapper.updateByPrimaryKeySelective(studentWorkVo);
    }

    /**
     * 修改学生作业信息
     *
     * @param studentWorkVo
     */
    @Override
    public void updateStudentWork(StudentWorkVo studentWorkVo) {
        this.studentWorkMapper.updateByPrimaryKeySelective(studentWorkVo);
    }

    /**
     * 根据用户id和作业id查询我提交的作业
     *
     * @param studentWorkVo
     * @return
     */
    @Override
    public DataGridView loadMyWork(StudentWorkVo studentWorkVo) {
        Page<Object> page = PageHelper.startPage(studentWorkVo.getPage(), studentWorkVo.getLimit());
        List<StudentWork> list = this.studentWorkMapper.loadMyWork(studentWorkVo);
        return new DataGridView(page.getTotal(), list);
    }
}
