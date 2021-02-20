package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.entity.SysNews;
import com.java.teaching.entity.SysNewsExample;
import com.java.teaching.service.NewsService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.common.vo.NewsVo;
import com.java.teaching.mapper.SysNewsMapper;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告管理接口的实现类
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private SysNewsMapper sysNewsMapper;

    /**
     * 查询全部公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<Object> page = PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());
        List<SysNews> sysNewsList = this.sysNewsMapper.selectByExample(new SysNewsExample());
        return new DataGridView(page.getTotal(), sysNewsList);
    }

    /**
     * 根据id查询一个公告
     *
     * @param id
     * @return
     */
    @Override
    public SysNews queryNewsById(Integer id) {
        return this.sysNewsMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加一个公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public void addNews(NewsVo newsVo) {
        if (newsVo != null) {
            this.sysNewsMapper.insertSelective(newsVo);
        }

    }

    /**
     * 修改一个公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public void updateNews(NewsVo newsVo) {
        this.sysNewsMapper.updateByPrimaryKeySelective(newsVo);
    }

    /**
     * 根据id删除一个公告
     *
     * @param id
     * @return
     */
    @Override
    public void deleteNews(Integer id) {
        if (id != null && id > 0) {
            this.sysNewsMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 批量删除公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public void batchDeleteNews(NewsVo newsVo) {
        for (Integer id : newsVo.getIds()) {
            this.deleteNews(id);
        }
    }

    /**
     * 模糊查询公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public List<SysNews> fuzzyQueryNews(NewsVo newsVo) {
        return this.sysNewsMapper.fuzzyQueryNews(newsVo);
    }
}
