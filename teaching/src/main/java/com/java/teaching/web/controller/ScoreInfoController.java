package com.java.teaching.web.controller;

import com.java.teaching.common.vo.ScoreInfoVo;
import com.java.teaching.service.ScoreInfoService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 成绩信息控制器
 */
@RestController
@RequestMapping(value = "/scoreInfo")
public class ScoreInfoController {

    @Autowired
    private ScoreInfoService scoreInfoService;

    /**
     * 保存学生成绩
     * @param scoreInfoVo
     * @return
     */
    @PostMapping(value = "/saveScore.action")
    public ResultObj saveScore(ScoreInfoVo scoreInfoVo) {
        try {
            //            先根据用户id和课程id查询该学生有没有成绩
            int i = this.scoreInfoService.queryScoreBySidAndCid(scoreInfoVo);
            if(i != 0) {
                this.scoreInfoService.updateScore(scoreInfoVo);
                return ResultObj.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

//        如果成绩表中没有成绩的话就插入成绩
        try {
            this.scoreInfoService.saveScore(scoreInfoVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据用户id查询成绩
     * @param scoreInfoVo
     * @return
     */
    @GetMapping(value = "/loadScoreById.action")
    public DataGridView loadScoreById(ScoreInfoVo scoreInfoVo) {
        return this.scoreInfoService.loadScoreById(scoreInfoVo);
    }

    /**
     * 根据课程名称查询成绩
     * @param scoreInfoVo
     * @return
     */
    @GetMapping(value = "/loadScoreByCourseName.action")
    public DataGridView loadScoreByCourseName(ScoreInfoVo scoreInfoVo) {
        return this.scoreInfoService.loadScoreByCourseName(scoreInfoVo);
    }
}
