package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.ScoreInfoVo;
import com.java.teaching.entity.ScoreInfo;
import com.java.teaching.entity.ScoreInfoExample;
import com.java.teaching.mapper.ScoreInfoMapper;
import com.java.teaching.service.ScoreInfoService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 成绩信息服务实现类
 */
@Service
public class ScoreInfoServiceImpl implements ScoreInfoService {

    @Autowired
    private ScoreInfoMapper scoreInfoMapper;

    /**
     * 保存学生成绩
     *
     * @param scoreInfoVo
     */
    @Override
    public void saveScore(ScoreInfoVo scoreInfoVo) {
        this.scoreInfoMapper.insert(scoreInfoVo);
    }

    /**
     * 根据用户id和课程id查询成绩
     *
     * @param scoreInfoVo
     * @return
     */
    @Override
    public int queryScoreBySidAndCid(ScoreInfoVo scoreInfoVo) {
        if(scoreInfoVo != null && scoreInfoVo.getSid() != null && scoreInfoVo.getCid() != null) {
            ScoreInfoExample example = new ScoreInfoExample();
            ScoreInfoExample.Criteria criteria = example.createCriteria();
            criteria.andCidEqualTo(scoreInfoVo.getCid());
            criteria.andSidEqualTo(scoreInfoVo.getSid());
            List<ScoreInfo> list = this.scoreInfoMapper.selectByExample(example);
            return list.size();
        }
        return 0;
    }

    /**
     * 修改成绩
     *
     * @param scoreInfoVo
     */
    @Override
    public void updateScore(ScoreInfoVo scoreInfoVo) {
        this.scoreInfoMapper.updateByPrimaryKeySelective(scoreInfoVo);
    }

    /**
     * 根据用户id查询成绩
     *
     * @param scoreInfoVo
     * @return
     */
    @Override
    public DataGridView loadScoreById(ScoreInfoVo scoreInfoVo) {
        Page<Object> page = PageHelper.startPage(scoreInfoVo.getPage(), scoreInfoVo.getLimit());
        List<ScoreInfoVo> list = this.scoreInfoMapper.loadScoreById(scoreInfoVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据课程名称查询成绩
     *
     * @param scoreInfoVo
     * @return
     */
    @Override
    public DataGridView loadScoreByCourseName(ScoreInfoVo scoreInfoVo) {
        Page<Object> page = PageHelper.startPage(scoreInfoVo.getPage(), scoreInfoVo.getLimit());
        List<ScoreInfoVo> list = this.scoreInfoMapper.loadScoreByCourseName(scoreInfoVo);
        return new DataGridView(page.getTotal(), list);
    }
}
