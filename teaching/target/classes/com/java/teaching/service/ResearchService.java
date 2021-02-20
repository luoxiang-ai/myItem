package com.java.teaching.service;

import com.java.teaching.common.vo.ResearchVo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 * 科研资料服务类
 */
public interface ResearchService {


    /**
     * 根据教师id加载科研资料
     * @param researchVo
     * @return
     */
    DataGridView loadResearchByTid(ResearchVo researchVo);

    /**
     * 修改科研资料
     * @param researchVo
     */
    void updateResearch(ResearchVo researchVo);

    /**
     * 添加科研资料
     * @param researchVo
     */
    void addResearch(ResearchVo researchVo);

    /**
     * 根据id删除科研资料
     * @param id
     */
    void deleteResearch(Integer id);

    /**
     * 批量删除科研资料
     * @param ids
     */
    void batchDeleteResearch(Integer[] ids);

    /**
     * 模糊查询科研资料信息
     * @param researchVo
     * @return
     */
    List<ResearchVo> fuzzyQueryResearch(ResearchVo researchVo);

    /**
     * 更新资源
     * @param researchVo
     */
    void updateResource(ResearchVo researchVo);
}
