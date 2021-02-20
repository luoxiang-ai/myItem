package com.java.teaching.web.controller;

import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.entity.Resource;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.ResourceService;
import com.java.teaching.utils.AppFileUtils;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 资源控制器
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源添加
     * @param resourceVo
     * @return
     */
    @PostMapping(value = "/addResource.action")
    public ResultObj addResource(ResourceVo resourceVo) {

        try {
            this.resourceService.addResource(resourceVo);
            return ResultObj.UPLOAD_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 加载全部资料
     * @param resourceVo
     * @return
     */
    @GetMapping(value = "/loadAllResource.action")
    public DataGridView loadAllResource(ResourceVo resourceVo) {
        return this.resourceService.loadAllResource(resourceVo);
    }

    /**
     * 根据课程id查询该课程的资料
     * @param resourceVo
     * @return
     */
    @GetMapping(value = "/loadResourceByCourseId.action")
    public DataGridView loadResourceByCourseId(ResourceVo resourceVo) {

        List<Resource> resourceList = this.resourceService.queryResourceByCourseId(resourceVo);
        return new DataGridView((long) resourceList.size(), resourceList);
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteResource.action")
    public ResultObj deleteResource(Integer id, String path) {
        try {

//            删除数据库记录
            this.resourceService.deleteResource(id);
//            删除硬盘资源
            AppFileUtils.deleteFile(AppFileUtils.PATH + path);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询资源
     * @return
     */
    @GetMapping(value = "/fuzzyQueryResource.action")
    public DataGridView fuzzyQueryResource(ResourceVo resourceVo) {
        List<ResourceVo> resourceList = this.resourceService.fuzzyQueryResource(resourceVo);
        return new DataGridView((long) resourceList.size(), resourceList);
    }

    /**
     * 更新资源信息
     * @param resourceVo
     * @return
     */
    @PostMapping(value = "/updateResource.action")
    public ResultObj updateResource(ResourceVo resourceVo) {
        try {
            this.resourceService.updateResource(resourceVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 加载推荐资源
     * @param resourceVo
     * @return
     */
    @GetMapping(value = "/loadRecommendResource.action")
    public DataGridView loadRecommendResource(ResourceVo resourceVo) {
        return this.resourceService.loadRecommendResource(resourceVo);
    }

    /**
     * 加载推荐书
     * @param resourceVo
     * @return
     */
    @GetMapping(value = "/loadRecommendBook.action")
    public DataGridView loadRecommendBook(ResourceVo resourceVo) {
        return this.resourceService.loadRecommendBook(resourceVo);
    }

    /**
     * 根据分类id查询资源
     * @param resourceVo
     * @return
     */
    @GetMapping(value = "/loadResourceBySortId.action")
    public DataGridView loadResourceBySortId(ResourceVo resourceVo) {
        return this.resourceService.loadResourceBySortId(resourceVo);
    }
}
