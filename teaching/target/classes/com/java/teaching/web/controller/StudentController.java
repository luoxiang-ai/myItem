package com.java.teaching.web.controller;

import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.SysUser;
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
 * 学生信息控制器
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private UserService studentService;

    /**
     * 加载全部学生
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadAllStudent.action")
    public DataGridView loadAllStudent(UserVo userVo) {
        return this.studentService.loadAllStudent(userVo);
    }

    /**
     * 修改学生
     * @param userVo
     * @return
     */
    @PostMapping(value = "/updateStudent.action")
    public ResultObj updateStudent(UserVo userVo) {
        try {
            this.studentService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加学生
     * @param userVo
     * @return
     */
    @PostMapping(value = "/addStudent.action")
    public ResultObj addStudent(UserVo userVo) {
        try {
            //           设置学生标识（3）
            userVo.setType(SysConstast.STUDENT_FLAG);
//            设置帐号位置备注
            userVo.setPosition("学生");
//            生成学号
            userVo.setLoginname(RandomUtils.createRandomStringUseTime(SysConstast.STUDENT_ID_PREFIX));
            this.studentService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id删除学生
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteStudent.action")
    public ResultObj deleteStudent(Integer id) {
        try {
            this.studentService.deleteUser(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除学生
     * @param userVo
     * @return
     */
    @PostMapping(value = "/batchDeleteStudent.action")
    public ResultObj batchDeleteStudent(UserVo userVo) {
        try {
            this.studentService.batchDeleteUser(userVo.getIds());
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
    @PostMapping(value = "/saveStudentRole.action")
    public ResultObj saveUserRole(UserVo userVo) {
        System.out.println(userVo);
        try {
            this.studentService.saveUserRole(userVo);
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
    @GetMapping(value = "/loadStudentRole.action")
    public DataGridView loadUserRole(UserVo userVo) {
        return this.studentService.queryUserRole(userVo);
    }

    /**
     * 重置密码
     * @param userVo
     * @return
     */
    @PostMapping(value = "/resetPassword")
    public ResultObj resetPassword(UserVo userVo) {
        try {
            this.studentService.resetUserPwd(userVo);
            return ResultObj.RESET_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 根据班级id查询学生
     * @param classId
     * @return
     */
    @GetMapping(value = "/loadStudentByClassId.action")
    public DataGridView loadStudentByClassId(String classId) {
        List<SysUser> userList = this.studentService.loadStudentByClassId(classId);

        return new DataGridView((long) userList.size(), userList);
    }

    /**
     * 模糊查询学生信息
     * @param userVo
     * @return
     */
    @GetMapping(value = "/fuzzyQueryStudent.action")
    public DataGridView fuzzyQueryStudent(UserVo userVo) {
        //        设置学生类型
        userVo.setType(SysConstast.STUDENT_FLAG);
        List<UserVo> list = this.studentService.fuzzyQueryStudent(userVo);
        return new DataGridView((long) list.size(), list);
    }

    /**
     * 根据课程id查询课程报名学生信息
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadStudentNumByCourseId.action")
    public DataGridView loadStudentNumByCourseId(UserVo userVo) {
        return this.studentService.loadStudentNumByCourseId(userVo);
    }
}
