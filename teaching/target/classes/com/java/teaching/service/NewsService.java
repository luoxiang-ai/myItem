package com.java.teaching.service;

import com.java.teaching.entity.SysNews;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.common.vo.NewsVo;
import com.java.teaching.utils.ResultObj;

import java.util.List;

/**
 * 公告管理的服务接口
 */
public interface NewsService {

    /**
     * 查询全部公告
     * @param newsVo
     * @return
     */
    public abstract DataGridView queryAllNews(NewsVo newsVo);

    /**
     * 根据id查询一个公告
     * @param Id
     * @return
     */
    public abstract SysNews queryNewsById(Integer Id);

    /**
     * 添加一个公告
     * @param newsVo
     * @return
     */
    public abstract void addNews(NewsVo newsVo);

    /**
     * 修改一个公告
     * @param newsVo
     * @return
     */
    public abstract void updateNews(NewsVo newsVo);

    /**
     * 根据id删除一个公告
     * @param id
     * @return
     */
    public abstract void deleteNews(Integer id);

    /**
     * 批量删除公告
     * @param newsVo
     * @return
     */
    public abstract void batchDeleteNews(NewsVo newsVo);

    /**
     * 模糊查询公告
     * @param newsVo
     * @return
     */
    List<SysNews> fuzzyQueryNews(NewsVo newsVo);
}
