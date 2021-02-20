package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.WorkVo;
import com.java.teaching.entity.Work;
import com.java.teaching.mapper.WorkMapper;
import com.java.teaching.service.WorkService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 作业服务类
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    /**
     * 加载全部作业
     *
     * @param workVo
     * @return
     */
    @Override
    public DataGridView loadAllWork(WorkVo workVo) {
        return null;
    }

    /**
     * 添加作业
     *
     * @param workVo
     */
    @Override
    public void addWork(WorkVo workVo) {
//        设置作业布置时间
        workVo.setStartTime(new Date());
        this.workMapper.insertSelective(workVo);
    }

    /**
     * 删除作业
     *
     * @param id
     */
    @Override
    public void deleteWork(Integer id) {

    }

    /**
     * 批量删除作业
     *
     * @param workVo
     */
    @Override
    public void batchDeleteWork(WorkVo workVo) {

    }

    /**
     * 修改作业信息
     *
     * @param workVo
     */
    @Override
    public void updateWork(WorkVo workVo) {

    }

    /**
     * 模糊查询作业
     *
     * @param workVo
     * @return
     */
    @Override
    public List<WorkVo> fuzzyQueryWork(WorkVo workVo) {
        return null;
    }

    /**
     * 根据课程id查询作业
     *
     * @param workVo
     * @return
     */
    @Override
    public DataGridView loadWorkByCourseId(WorkVo workVo) {
        Page<Object> page = PageHelper.startPage(workVo.getPage(), workVo.getLimit());
        List<Work> list = this.workMapper.loadWorkByCourseId(workVo);
        return new DataGridView(page.getTotal(), list);
    }
}
