package com.java.teaching.web.controller;

import com.java.teaching.common.vo.ResearchVo;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.ResearchService;
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
 * 科研资源控制器
 */
@RestController
@RequestMapping(value = "/research")
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    /**
     * 根据教师id加载科研资料
     * @param researchVo
     * @return
     */
    @GetMapping(value = "/loadResearchByTid.action")
    public DataGridView loadResearchByTid(ResearchVo researchVo) {
        return this.researchService.loadResearchByTid(researchVo);
    }

    /**
     * 修改科研资料
     * @param researchVo
     * @return
     */
    @PostMapping(value = "/updateResearch.action")
    public ResultObj updateResearch(ResearchVo researchVo, HttpSession session) {
        try {
            //            设置教师id
            SysUser user = (SysUser) session.getAttribute("sysUser");
            researchVo.setTid(user.getUserid());
            this.researchService.updateResearch(researchVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加科研资料
     * @param researchVo
     * @return
     */
    @PostMapping(value = "/addResearch.action")
    public ResultObj addResearch(ResearchVo researchVo, HttpSession session) {
        try {
//            设置教师id
            SysUser user = (SysUser) session.getAttribute("sysUser");
            researchVo.setTid(user.getUserid());
            this.researchService.addResearch(researchVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id删除科研资料
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteResearch.action")
    public ResultObj deleteResearch(Integer id) {
        try {
            this.researchService.deleteResearch(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除科研资料
     * @param researchVo
     * @return
     */
    @PostMapping(value = "/batchDeleteResearch.action")
    public ResultObj batchDeleteResearch(ResearchVo researchVo) {
        try {
            this.researchService.batchDeleteResearch(researchVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 模糊查询科研资料信息
     * @param researchVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryResearch.action")
    public DataGridView fuzzyQueryResearch(ResearchVo researchVo, HttpSession session) {
//        设置老师id
        SysUser user = (SysUser) session.getAttribute("sysUser");
        researchVo.setTid(researchVo.getId());
        List<ResearchVo> list = this.researchService.fuzzyQueryResearch(researchVo);
        return new DataGridView((long) list.size(), list);
    }

    /**
     * 更新资源
     * @param researchVo
     * @return
     */
    @PostMapping(value = "/updateResource.action")
    public ResultObj updateResource(ResearchVo researchVo) {
        try {
            this.researchService.updateResource(researchVo);
            return ResultObj.UPLOAD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPLOAD_ERROR;
        }
    }

}
