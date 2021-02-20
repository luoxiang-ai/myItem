package com.java.teaching.web.controller;

import com.java.teaching.common.vo.ResourceSortVo;
import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.ResourceSortService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/resourceSort")
public class ResourceSortController {


    @Autowired
    private ResourceSortService resourceSortService;

    /**
     * 加载全部资源分类
     * @param resourceSortVo
     * @return
     */
    @GetMapping(value = "/loadAllResourceSort.action")
    public DataGridView loadAllResourceSort(ResourceSortVo resourceSortVo) {
        return this.resourceSortService.loadAllResourceSort(resourceSortVo);
    }

    /**
     * 修改资源分类
     * @param resourceSortVo
     * @return
     */
    @PostMapping(value = "/updateResourceSort.action")
    public ResultObj updateResourceSort(ResourceSortVo resourceSortVo) {
        try {
            this.resourceSortService.updateResourceSort(resourceSortVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加资源分类
     * @param resourceSortVo
     * @return
     */
    @PostMapping(value = "/addResourceSort.action")
    public ResultObj addResourceSort(ResourceSortVo resourceSortVo, HttpSession session) {
        try {
            //            获取用户信息
            SysUser sysUser = (SysUser) session.getAttribute("sysUser");
            this.resourceSortService.addResourceSort(resourceSortVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据编号删除班级
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteResourceSort.action")
    public ResultObj deleteResourceSort(Integer id) {
        try {
            this.resourceSortService.deleteResourceSort(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询资源分类
     * @return
     */
    @GetMapping(value = "/fuzzyQueryResourceSort.action")
    public DataGridView fuzzyQueryResourceSort(ResourceSortVo resourceSortVo) {
        List<ResourceSortVo> resourceList = this.resourceSortService.fuzzyQueryResourceSort(resourceSortVo);
        return new DataGridView((long) resourceList.size(), resourceList);
    }
}
