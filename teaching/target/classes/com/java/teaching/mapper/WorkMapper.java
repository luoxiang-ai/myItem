package com.java.teaching.mapper;

import com.java.teaching.common.vo.WorkVo;
import com.java.teaching.entity.Work;
import com.java.teaching.entity.WorkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    int countByExample(WorkExample example);

    int deleteByExample(WorkExample example);

    int deleteByPrimaryKey(Integer wid);

    int insert(Work record);

    int insertSelective(Work record);

    List<Work> selectByExampleWithBLOBs(WorkExample example);

    List<Work> selectByExample(WorkExample example);

    Work selectByPrimaryKey(Integer wid);

    int updateByExampleSelective(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExampleWithBLOBs(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKeyWithBLOBs(Work record);

    int updateByPrimaryKey(Work record);

    /**
     * 根据课程id查询作业
     * @param workVo
     * @return
     */
    List<Work> loadWorkByCourseId(WorkVo workVo);
}