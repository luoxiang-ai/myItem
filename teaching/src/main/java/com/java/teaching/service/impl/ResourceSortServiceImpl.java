package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.ResourceSortVo;
import com.java.teaching.common.vo.ResourceSortVo;
import com.java.teaching.entity.ResourceSort;
import com.java.teaching.entity.ResourceSortExample;
import com.java.teaching.mapper.ResourceSortMapper;
import com.java.teaching.service.ResourceSortService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源分类服务类
 */
@Service
public class ResourceSortServiceImpl implements ResourceSortService {

    @Autowired
    private ResourceSortMapper resourceSortMapper;

    /**
     * 加载全部资源分类
     *
     * @param resourceSortVo
     * @return
     */
    @Override
    public DataGridView loadAllResourceSort(ResourceSortVo resourceSortVo) {
        List<ResourceSort> sortList = null;
        if(resourceSortVo != null && resourceSortVo.getPage() != null && resourceSortVo.getLimit() != null) {
            Page<Object> page = PageHelper.startPage(resourceSortVo.getPage(), resourceSortVo.getLimit());
            sortList = this.resourceSortMapper.selectByExample(new ResourceSortExample());
            return new DataGridView(page.getTotal(), sortList);
        }

        sortList = this.resourceSortMapper.selectByExample(new ResourceSortExample());
        return new DataGridView(sortList);
    }

    /**
     * 添加资源分类
     *
     * @param resourceSortVo
     */
    @Override
    public void addResourceSort(ResourceSortVo resourceSortVo) {
        this.resourceSortMapper.insertSelective(resourceSortVo);
    }

    /**
     * 删除资源分类
     *
     * @param id
     */
    @Override
    public void deleteResourceSort(Integer id) {
        this.resourceSortMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除资源分类
     *
     * @param resourceSortVo
     */
    @Override
    public void batchDeleteResourceSort(ResourceSortVo resourceSortVo) {

    }

    /**
     * 修改资源分类信息
     *
     * @param resourceSortVo
     */
    @Override
    public void updateResourceSort(ResourceSortVo resourceSortVo) {
        this.resourceSortMapper.updateByPrimaryKeySelective(resourceSortVo);
    }

    /**
     * 模糊查询资源分类
     *
     * @param resourceSortVo
     * @return
     */
    @Override
    public List<ResourceSortVo> fuzzyQueryResourceSort(ResourceSortVo resourceSortVo) {
        Page<Object> page = PageHelper.startPage(resourceSortVo.getPage(), resourceSortVo.getLimit());
        List<ResourceSortVo> sortList = this.resourceSortMapper.fuzzyQueryResourceSort(resourceSortVo);
        return sortList;
    }
}
