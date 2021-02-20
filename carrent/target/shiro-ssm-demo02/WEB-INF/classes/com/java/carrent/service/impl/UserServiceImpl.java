package com.java.carrent.service.impl;

import com.java.carrent.entity.SysUser;
import com.java.carrent.entity.SysUserExample;
import com.java.carrent.mapper.SysUserMapper;
import com.java.carrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser queryUserByUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            SysUserExample sysUserExample = new SysUserExample();
            SysUserExample.Criteria criteria = sysUserExample.createCriteria();
            criteria.andLoginnameEqualTo(username);
            List<SysUser> sysUsers = this.sysUserMapper.selectByExample(sysUserExample);
            if (sysUsers != null && !sysUsers.isEmpty()) {
                return sysUsers.get(0);
            }
        }
        return null;
    }
}
