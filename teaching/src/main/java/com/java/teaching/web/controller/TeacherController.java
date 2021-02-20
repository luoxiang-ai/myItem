package com.java.teaching.web.controller;

import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.service.UserService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.RandomUtils;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 教师控制器
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private UserService teacherService;

    /**
     * 加载全部老师
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadAllTeacher.action")
    public DataGridView loadAllTeacher(UserVo userVo) {
        return this.teacherService.loadAllTeacher(userVo);
    }

    /**
     * 修改老师
     * @param userVo
     * @return
     */
    @PostMapping(value = "/updateTeacher.action")
    public ResultObj updateTeacher(UserVo userVo) {
        try {
            this.teacherService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加老师
     * @param userVo
     * @return
     */
    @PostMapping(value = "/addTeacher.action")
    public ResultObj addTeacher(UserVo userVo) {
        try {
//            设置老师标识
            userVo.setType(SysConstast.TEACHER_FLAG);
            userVo.setPosition("老师");
//            生成教师编号
            userVo.setLoginname(RandomUtils.createRandomStringUseTime(SysConstast.TEACHER_ID_PREFIX));
            this.teacherService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id删除老师
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteTeacher.action")
    public ResultObj deleteTeacher(Integer id) {
        try {
            this.teacherService.deleteUser(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     * @param userVo
     * @return
     */
    @PostMapping(value = "/batchDeleteTeacher.action")
    public ResultObj batchDeleteTeacher(UserVo userVo) {
        try {
            this.teacherService.batchDeleteUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 保存用户和角色的关系
     * @param userVo
     * @return
     */
    @PostMapping(value = "/saveTeacherRole.action")
    public ResultObj saveUserRole(UserVo userVo) {
        System.out.println(userVo);
        try {
            this.teacherService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 加载用户拥有的角色
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadTeacherRole.action")
    public DataGridView loadUserRole(UserVo userVo) {
        return this.teacherService.queryUserRole(userVo);
    }

    /**
     * 模糊查询老师信息
     * @param userVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryTeacher.action")
    public DataGridView fuzzyQueryTeacher(UserVo userVo) {
//        设置老师类型
        userVo.setType(SysConstast.TEACHER_FLAG);
        List<UserVo> list = this.teacherService.fuzzyQueryStudent(userVo);
        return new DataGridView((long) list.size(), list);
    }
}
