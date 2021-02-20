package com.java.teaching.web.controller;

import com.java.teaching.common.vo.ClassVo;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.ClassInfo;
import com.java.teaching.service.ClassService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/class")
public class ClassInfoController {

    @Autowired
    private ClassService classService;

    /**
     * 加载全部班级
     * @param classVo
     * @return
     */
    @GetMapping(value = "/loadAllClass.action")
    public DataGridView loadAllClass(ClassVo classVo) {
        return this.classService.loadAllClass(classVo);
    }

    /**
     * 修改班级
     * @param classVo
     * @return
     */
    @PostMapping(value = "/updateClass.action")
    public ResultObj updateClass(ClassVo classVo) {
        try {
            this.classService.updateClass(classVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加班级
     * @param classVo
     * @return
     */
    @PostMapping(value = "/addClass.action")
    public ResultObj addClass(ClassVo classVo) {
        try {
            this.classService.addClass(classVo);
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
    @PostMapping(value = "/deleteClass.action")
    public ResultObj deleteClass(String id) {
        try {
            this.classService.deleteClass(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param classVo
     * @return
     */
    @PostMapping(value = "/batchDeleteClass.action")
    public ResultObj batchDeleteClass(ClassVo classVo) {
        try {
            this.classService.batchDeleteClass(classVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (RuntimeException e1) {
//            班级下有学生的情况
            return ResultObj.DELETE_CLASS_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询班级信息
     * @param classVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryClass.action")
    public DataGridView fuzzyQueryClass(ClassVo classVo) {
        List<ClassInfo> list = this.classService.fuzzyQueryClass(classVo);
        return new DataGridView((long) list.size(), list);
    }
}
