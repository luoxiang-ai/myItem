package com.java.teaching.web.controller;

import java.util.Date;
import java.util.List;

import com.java.teaching.common.vo.NewsVo;
import com.java.teaching.entity.SysNews;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.NewsService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;

/**
 * 公告管理控制器
 * 
 */
@RestController
@RequestMapping("news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	/**
	 * 加载公告列表返回DataGridView
	 */
	@RequestMapping("loadAllNews")
	public DataGridView loadAllNews(NewsVo newsVo) {
		return this.newsService.queryAllNews(newsVo);
	}
	
	/**
	 * 添加公告
	 */
	@RequestMapping("addNews")
	public ResultObj addNews(NewsVo newsVo, HttpSession session) {
		try {
			newsVo.setCreatetime(new Date());
			SysUser user = (SysUser) session.getAttribute("sysUser");
			newsVo.setOpername(user.getRealname());
			this.newsService.addNews(newsVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改公告
	 */
	@RequestMapping("updateNews")
	public ResultObj updateNews(NewsVo newsVo) {
		try {
			this.newsService.updateNews(newsVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 删除公告
	 */
	@RequestMapping("deleteNews")
	public ResultObj deleteNews(NewsVo newsVo) {
		try {
			this.newsService.deleteNews(newsVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除公告
	 */
	@RequestMapping("deleteBatchNews")
	public ResultObj deleteBatchNews(NewsVo newsVo) {
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
