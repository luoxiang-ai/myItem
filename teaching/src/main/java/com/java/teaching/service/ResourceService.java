package com.java.teaching.service;


import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.entity.Resource;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 * 资料服务类
 */
public interface ResourceService {

    /**
     * 添加资源
     * @param resourceVo
     */
    void addResource(ResourceVo resourceVo);

    /**
     * 加载全部资源
     * @param resourceVo
     * @return
     */
    DataGridView loadAllResource(ResourceVo resourceVo);

    /**
     * 根据课程id查询该课程的资料
     * @param resourceVo
     * @return
     */
    List<Resource> queryResourceByCourseId(ResourceVo resourceVo);

    /**
     * 根据id删除资源
     * @param id
     */
    void deleteResource(Integer id);

    /**
     * 模糊查询资料
     * @param resourceVo
     * @return
     */
    List<ResourceVo> fuzzyQueryResource(ResourceVo resourceVo);

    /**
     * 更新资源信息
     * @param resourceVo
     */
    void updateResource(ResourceVo resourceVo);

    /**
     * 加载推荐资源
     * @param resourceVo
     * @return
     */
    DataGridView loadRecommendResource(ResourceVo resourceVo);

    /**
     * 加载推荐书
     * @param resourceVo
     * @return
     */
    DataGridView loadRecommendBook(ResourceVo resourceVo);

    /**
     * 根据分类id查询资源
     * @param resourceVo
     * @return
     */
    DataGridView loadResourceBySortId(ResourceVo resourceVo);
}
