package com.java.teaching.mapper;

import com.java.teaching.common.vo.StudentWorkVo;
import com.java.teaching.entity.StudentWork;
import com.java.teaching.entity.StudentWorkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentWorkMapper {
    int countByExample(StudentWorkExample example);

    int deleteByExample(StudentWorkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentWork record);

    int insertSelective(StudentWork record);

    List<StudentWork> selectByExample(StudentWorkExample example);

    StudentWork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentWork record, @Param("example") StudentWorkExample example);

    int updateByExample(@Param("record") StudentWork record, @Param("example") StudentWorkExample example);

    int updateByPrimaryKeySelective(StudentWork record);

    int updateByPrimaryKey(StudentWork record);

    /**
     * 根据作业布置id查询作业提交情况
     * @param studentWorkVo
     * @return
     */
    List<StudentWorkVo> loadStudentWorkByWorkId(StudentWorkVo studentWorkVo);

    /**
     * 根据用户id和作业id查询我提交的作业
     * @param studentWorkVo
     * @return
     */
    List<StudentWork> loadMyWork(StudentWorkVo studentWorkVo);
}