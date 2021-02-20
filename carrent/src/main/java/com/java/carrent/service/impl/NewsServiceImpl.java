package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.entity.SysNews;
import com.java.carrent.entity.SysNewsExample;
import com.java.carrent.service.NewsService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.common.vo.NewsVo;
import com.java.carrent.mapper.SysNewsMapper;
import com.java.carrent.utils.ResultObj;
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
        SysNewsExample example = new SysNewsExample();
        example.setOrderByClause("createtime DESC");
        List<SysNews> sysNewsList = this.sysNewsMapper.selectByExample(example);
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
    public ResultObj addNews(NewsVo newsVo) {
        if (newsVo != null) {
            try {
                this.sysNewsMapper.insertSelective(newsVo);
                return ResultObj.ADD_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.ADD_ERROR;
            }
        }

        return null;
    }

    /**
     * 修改一个公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public ResultObj updateNews(NewsVo newsVo) {
        if (newsVo != null) {
            try {
                this.sysNewsMapper.updateByPrimaryKeySelective(newsVo);
                return ResultObj.UPDATE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.UPDATE_ERROR;
            }
        }
        return null;
    }

    /**
     * 根据id删除一个公告
     *
     * @param id
     * @return
     */
    @Override
    public ResultObj deleteNews(Integer id) {
        if (id != null && id > 0) {
            try {
                this.sysNewsMapper.deleteByPrimaryKey(id);
                return ResultObj.DELETE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.RESET_ERROR;
            }
        }
        return null;
    }

    /**
     * 批量删除公告
     *
     * @param newsVo
     * @return
     */
    @Override
    public ResultObj batchDeleteNews(NewsVo newsVo) {
        if (newsVo != null) {
            try {
                for (Integer id : newsVo.getIds()) {
                    this.deleteNews(id);
                }
                return ResultObj.DELETE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.DELETE_ERROR;
            }
        }
        return null;
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
