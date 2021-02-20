package com.java.teaching.service;

import com.java.teaching.common.vo.WorkVo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 * 作业服务类
 */
public interface WorkService {

    /**
     * 加载全部作业
     * @param workVo
     * @return
     */
    DataGridView loadAllWork(WorkVo workVo);

    /**
     * 添加作业
     * @param workVo
     */
    void addWork(WorkVo workVo);

    /**
     * 删除作业
     * @param id
     */
    void deleteWork(Integer id);

    /**
     * 批量删除作业
     * @param workVo
     */
    void batchDeleteWork(WorkVo workVo);

    /**
     * 修改作业信息
     * @param workVo
     */
    void updateWork(WorkVo workVo);

    /**
     * 模糊查询作业
     * @param workVo
     * @return
     */
    List<WorkVo> fuzzyQueryWork(WorkVo workVo);

    /**
     * 根据课程id查询作业
     * @param workVo
     * @return
     */
    DataGridView loadWorkByCourseId(WorkVo workVo);
}
