package com.java.carrent.service;

import com.java.carrent.entity.SysUser;
import com.java.carrent.common.vo.UserVo;
import com.java.carrent.utils.DataGridView;

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
     * @param userId
     */
    public abstract void resetUserPwd(Integer userId);

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
    public abstract SysUser queryUserByUserId(String userId);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public abstract SysUser login(String username, String password);
}
