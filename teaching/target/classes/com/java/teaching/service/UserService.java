package com.java.teaching.service;

import com.java.teaching.entity.SysUser;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.utils.DataGridView;

import java.util.List;

/**
 *
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public abstract SysUser queryUserByUsername(String username);

    /**
     * 查询全部用户
     * @param userVo
     * @return
     */
    public abstract DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     * @param userVo
     */
    public abstract void addUser(UserVo userVo) throws ClassNotFoundException;

    /**
     * 修改用户
     * @param userVo
     */
    public abstract void updateUser(UserVo userVo);

    /**
     * 删除用户
     * @param userId
     */
    public abstract void deleteUser(Integer userId);

    /**
     * 批量删除用户
     * @param ids
     */
    public abstract void batchDeleteUser(Integer[] ids);

    /**
     * 重置密码
     * @param userVo
     */
    public abstract void resetUserPwd(UserVo userVo);

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    public abstract void saveUserRole(UserVo userVo);

    /**
     * 加载用户所拥有的角色
     * @param userVo
     * @return
     */
    public abstract DataGridView queryUserRole(UserVo userVo);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    public abstract SysUser queryUserByUserId(Integer userId);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public abstract SysUser login(String username, String password);

    /**
     * 加载全部学生
     * @param userVo
     * @return
     */
    DataGridView loadAllStudent(UserVo userVo);

    /**
     * 加载全部老师
     * @param userVo
     * @return
     */
    DataGridView loadAllTeacher(UserVo userVo);

    /**
     * 根据班级id查询学生
     * @param classId
     * @return
     */
    List<SysUser> loadStudentByClassId(String classId);

    /**
     * 修改密码
     * @param userVo
     */
    void updatePwd(UserVo userVo) throws Exception;

    /**
     * 模糊查询学生
     * @param userVo
     * @return
     */
    List<UserVo> fuzzyQueryStudent(UserVo userVo);

    /**
     * 验证旧密码
     * @param userVo
     * @return
     */
    int queryUserByUserIdAndPassword(UserVo userVo);

    /**
     * 根据课程id查询课程报名学生信息
     * @param userVo
     * @return
     */
    DataGridView loadStudentNumByCourseId(UserVo userVo);
}
