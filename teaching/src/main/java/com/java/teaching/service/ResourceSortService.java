package com.java.teaching.service;

import com.java.teaching.common.vo.ResourceSortVo;
import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

public interface ResourceSortService {

    /**
     * 加载全部资源分类
     * @param resourceSortVo
     * @return
     */
    DataGridView loadAllResourceSort(ResourceSortVo resourceSortVo);

    /**
     * 添加资源分类
     * @param resourceSortVo
     */
    void addResourceSort(ResourceSortVo resourceSortVo);

    /**
     * 删除资源分类
     * @param id
     */
    void deleteResourceSort(Integer id);

    /**
     * 批量删除资源分类
     * @param resourceSortVo
     */
    void batchDeleteResourceSort(ResourceSortVo resourceSortVo);

    /**
     * 修改资源分类信息
     * @param resourceSortVo
     */
    void updateResourceSort(ResourceSortVo resourceSortVo);

    /**
     * 模糊查询资源分类
     * @param resourceSortVo
     * @return
     */
    List<ResourceSortVo> fuzzyQueryResourceSort(ResourceSortVo resourceSortVo);
}
