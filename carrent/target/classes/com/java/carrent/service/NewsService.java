package com.java.carrent.service;

import com.java.carrent.entity.SysNews;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.common.vo.NewsVo;
import com.java.carrent.utils.ResultObj;

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
    public abstract ResultObj addNews(NewsVo newsVo);

    /**
     * 修改一个公告
     * @param newsVo
     * @return
     */
    public abstract ResultObj updateNews(NewsVo newsVo);

    /**
     * 根据id删除一个公告
     * @param id
     * @return
     */
    public abstract ResultObj deleteNews(Integer id);

    /**
     * 批量删除公告
     * @param newsVo
     * @return
     */
    public abstract ResultObj batchDeleteNews(NewsVo newsVo);

    /**
     * 模糊查询公告
     * @param newsVo
     * @return
     */
    List<SysNews> fuzzyQueryNews(NewsVo newsVo);
}
