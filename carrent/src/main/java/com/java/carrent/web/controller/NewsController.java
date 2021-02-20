package com.java.carrent.web.controller;

import com.java.carrent.common.vo.NewsVo;
import com.java.carrent.entity.SysNews;
import com.java.carrent.entity.SysUser;
import com.java.carrent.service.NewsService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 公告管理控制器
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 加载全部公告信息
     * @param newsVo
     * @return
     */
    @GetMapping(value = "/loadAllNews.action")
    public DataGridView loadAllNews(NewsVo newsVo) {
        return this.newsService.queryAllNews(newsVo);
    }

    /**
     * 修改公告
     * @param newsVo
     * @return
     */
    @PostMapping(value = "/updateNews.action")
    public ResultObj updateNews(NewsVo newsVo) {
        try {
            this.newsService.updateNews(newsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加公告
     * @param newsVo
     * @param session
     * @return
     */
    @PostMapping(value = "/addNews.action")
    public ResultObj addNews(NewsVo newsVo, HttpSession session) {
        try {
            newsVo.setCreatetime(new Date());
            SysUser sysUser = (SysUser) session.getAttribute("sysUser");
            newsVo.setOpername(sysUser.getLoginname());
            this.newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteNews.action")
    public ResultObj deleteNews(Integer id) {
        try {
            this.newsService.deleteNews(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param newsVo
     * @return
     */
    @PostMapping(value = "/batchDeleteNews.action")
    public ResultObj batchDeleteNews(NewsVo newsVo) {
        try {
            this.newsService.batchDeleteNews(newsVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @RequestMapping("loadNewsById")
    public SysNews loadNewsById(Integer id) {
        return this.newsService.queryNewsById(id);
    }

    /**
     * 模糊查询公告
     * @return
     */
    @GetMapping(value = "/fuzzyQueryNews.action")
    public DataGridView fuzzyQueryNews(NewsVo newsVo) {
        List<SysNews> newsList = this.newsService.fuzzyQueryNews(newsVo);

        return new DataGridView((long) newsList.size(), newsList);
    }
}
