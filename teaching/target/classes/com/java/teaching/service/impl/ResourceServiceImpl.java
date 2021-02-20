package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.entity.Resource;
import com.java.teaching.entity.ResourceExample;
import com.java.teaching.mapper.ResourceMapper;
import com.java.teaching.service.ResourceService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 添加资源
     *
     * @param resourceVo
     */
    @Override
    public void addResource(ResourceVo resourceVo) {
        if(resourceVo != null) {
            resourceVo.setFlag(false);
            this.resourceMapper.insertSelective(resourceVo);
        }
    }

    /**
     * 加载全部资源
     *
     * @param resourceVo
     * @return
     */
    @Override
    public DataGridView loadAllResource(ResourceVo resourceVo) {
        Page<Object> page = PageHelper.startPage(resourceVo.getPage(), resourceVo.getLimit());
//        List<ResourceVo> resourceList = this.resourceMapper.queryAllResource(resourceVo);
        List<ResourceVo> resourceList = this.resourceMapper.queryAllResource(resourceVo);

        return new DataGridView(page.getTotal(), resourceList);
    }

    /**
     * 根据课程id查询该课程的资料
     *
     * @param resourceVo
     * @return
     */
    @Override
    public List<Resource> queryResourceByCourseId(ResourceVo resourceVo) {
        if(resourceVo != null) {
            return this.resourceMapper.queryResourceByCourseId(resourceVo.getcId());
        }
        return null;
    }

    /**
     * 根据id删除资源
     *
     * @param id
     */
    @Override
    public void deleteResource(Integer id) {
        this.resourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 模糊查询资料
     *
     * @param resourceVo
     * @return
     */
    @Override
    public List<ResourceVo> fuzzyQueryResource(ResourceVo resourceVo) {
        return this.resourceMapper.fuzzyQueryResource(resourceVo);
    }

    /**
     * 更新资源信息
     *
     * @param resourceVo
     */
    @Override
    public void updateResource(ResourceVo resourceVo) {
        this.resourceMapper.updateByPrimaryKeySelective(resourceVo);
    }

    /**
     * 加载推荐资源
     *
     * @param resourceVo
     * @return
     */
    @Override
    public DataGridView loadRecommendResource(ResourceVo resourceVo) {
        Page<Object> page = PageHelper.startPage(resourceVo.getPage(), resourceVo.getLimit());
        resourceVo.setFlag(true);
        resourceVo.setsId(1);
        List<ResourceVo> resourceList = this.resourceMapper.loadRecommendResource(resourceVo);

        return new DataGridView(page.getTotal(), resourceList);
    }

    /**
     * 加载推荐书
     *
     * @param resourceVo
     * @return
     */
    @Override
    public DataGridView loadRecommendBook(ResourceVo resourceVo) {
        Page<Object> page = PageHelper.startPage(resourceVo.getPage(), resourceVo.getLimit());
        resourceVo.setFlag(true);
        resourceVo.setsId(1);
        List<ResourceVo> resourceList = this.resourceMapper.loadRecommendBook(resourceVo);

        return new DataGridView(page.getTotal(), resourceList);
    }

    /**
     * 根据分类id查询资源
     *
     * @param resourceVo
     * @return
     */
    @Override
    public DataGridView loadResourceBySortId(ResourceVo resourceVo) {
        Page<Object> page = PageHelper.startPage(resourceVo.getPage(), resourceVo.getLimit());
        List<ResourceVo> resourceList = this.resourceMapper.loadResourceBySortId(resourceVo);

        return new DataGridView(page.getTotal(), resourceList);
    }
}
