package com.java.teaching.mapper;

import com.java.teaching.common.vo.ScoreInfoVo;
import com.java.teaching.entity.ScoreInfo;
import com.java.teaching.entity.ScoreInfoExample;
import com.java.teaching.entity.ScoreInfoKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreInfoMapper {
    int countByExample(ScoreInfoExample example);

    int deleteByExample(ScoreInfoExample example);

    int deleteByPrimaryKey(ScoreInfoKey key);

    int insert(ScoreInfo record);

    int insertSelective(ScoreInfo record);

    List<ScoreInfo> selectByExample(ScoreInfoExample example);

    ScoreInfo selectByPrimaryKey(ScoreInfoKey key);

    int updateByExampleSelective(@Param("record") ScoreInfo record, @Param("example") ScoreInfoExample example);

    int updateByExample(@Param("record") ScoreInfo record, @Param("example") ScoreInfoExample example);

    int updateByPrimaryKeySelective(ScoreInfo record);

    int updateByPrimaryKey(ScoreInfo record);

    /**
     * 根据用户id查询成绩
     * @param scoreInfoVo
     * @return
     */
    List<ScoreInfoVo> loadScoreById(ScoreInfoVo scoreInfoVo);

    /**
     * 根据课程名称查询成绩
     * @param scoreInfoVo
     * @return
     */
    List<ScoreInfoVo> loadScoreByCourseName(ScoreInfoVo scoreInfoVo);
}