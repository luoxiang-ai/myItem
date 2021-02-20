package com.java.carrent.service;

import com.java.carrent.entity.SysUser;

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
}
