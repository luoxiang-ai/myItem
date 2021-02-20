package com.java.teaching.service;

import com.java.teaching.common.vo.ScoreInfoVo;
import com.java.teaching.utils.DataGridView;

/**
 * 成绩信息服务类型
 */
public interface ScoreInfoService {

    /**
     * 保存学生成绩
     * @param scoreInfoVo
     */
    void saveScore(ScoreInfoVo scoreInfoVo);

    /**
     * 根据用户id和课程id查询成绩
     * @param scoreInfoVo
     * @return
     */
    int queryScoreBySidAndCid(ScoreInfoVo scoreInfoVo);

    /**
     * 修改成绩
     * @param scoreInfoVo
     */
    void updateScore(ScoreInfoVo scoreInfoVo);

    /**
     * 根据用户id查询成绩
     * @param scoreInfoVo
     * @return
     */
    DataGridView loadScoreById(ScoreInfoVo scoreInfoVo);

    /**
     * 根据课程名称查询成绩
     * @param scoreInfoVo
     * @return
     */
    DataGridView loadScoreByCourseName(ScoreInfoVo scoreInfoVo);
}
