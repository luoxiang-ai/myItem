package com.java.carrent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.carrent.service.UserService;
import com.java.carrent.common.vo.UserVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.*;
import com.java.carrent.mapper.SysRoleMapper;
import com.java.carrent.mapper.SysRoleUserMapper;
import com.java.carrent.mapper.SysUserMapper;
import com.java.carrent.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

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

    /**
     * 查询全部用户
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        if (userVo != null) {
            Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
            List<SysUser> userList = this.sysUserMapper.selectByExample(new SysUserExample());
            return new DataGridView(page.getTotal(), userList);
        }
        return null;
    }

    /**
     * 添加用户
     *
     * @param userVo
     */
    @Override
    public void addUser(UserVo userVo) throws ClassNotFoundException {
       if (userVo != null) {
           this.sysUserMapper.insertSelective(userVo);
       }
    }

    /**
     * 修改用户
     *
     * @param userVo
     */
    @Override
    public void updateUser(UserVo userVo) {
        if (userVo != null) {
            this.sysUserMapper.updateByPrimaryKeySelective(userVo);
        }
    }

    /**
     * 保存用户和角色的关系
     *
     * @param userVo
     */
    @Override
    public void saveUserRole(UserVo userVo) {
        if (userVo != null) {
            Integer userId = userVo.getUserid();
//            插入新的关系前，先删除之前的旧关系
            SysRoleUserExample sysRoleUserExample = new SysRoleUserExample();
            SysRoleUserExample.Criteria criteria = sysRoleUserExample.createCriteria();
            criteria.andUidEqualTo(userId);
            this.sysRoleUserMapper.deleteByExample(sysRoleUserExample);
            Integer[] roleIds = userVo.getIds();
            if (roleIds != null && roleIds.length > 0) {
                for (Integer roleId : roleIds) {
                    //            保存新关系
                    SysRoleUserKey sysRoleUser = new SysRoleUserKey();
                    sysRoleUser.setUid(userId);
                    sysRoleUser.setRid(roleId);
                    this.sysRoleUserMapper.insertSelective(sysRoleUser);
                }
            }
        }
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    public void deleteUser(Integer userId) {
        if (userId != null && userId > 0) {
            this.sysUserMapper.deleteByPrimaryKey(userId);
        }
    }

    /**
     * 批量删除用户
     *
     * @param ids
     */
    @Override
    public void batchDeleteUser(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteUser(id);
        }
    }

    /**
     * 重置密码
     *
     * @param userId
     */
    @Override
    public void resetUserPwd(Integer userId) {
        if (userId != null && userId > 0) {

        }
    }

    /**
     * 加载用户所拥有的角色
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView queryUserRole(UserVo userVo) {
//        1.查询所有可用的角色
        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
        criteria.andAvailableEqualTo(SysConstast.AVAILABLE_TRUE);
        List<SysRole> allRole = this.sysRoleMapper.selectByExample(sysRoleExample);

//        2.根据用户id查询该用户所拥有的角色
        SysRoleUserExample sysRoleUserExample = new SysRoleUserExample();
        SysRoleUserExample.Criteria criteria1 = sysRoleUserExample.createCriteria();
        criteria1.andUidEqualTo(userVo.getUserid());
        List<SysRoleUserKey> userRole = this.sysRoleUserMapper.selectByExample(sysRoleUserExample);

        List<Map<String, Object>> data = new ArrayList<>();
        for (SysRole role1 : allRole) {
            Boolean LAY_CHECKED = false;
            for (SysRoleUserKey role2 : userRole) {
                if (role1.getRoleid() == role2.getRid()) {
                    LAY_CHECKED = true;
                }
            }

            Map<String, Object> map = new HashMap<>();
            map.put("roleid", role1.getRoleid());
            map.put("rolename", role1.getRolename());
            map.put("roledesc", role1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser queryUserByUserId(String userId) {
        if(userId != null && !userId.trim().isEmpty()) {
            int id = Integer.parseInt(userId);
            if(id > 0) {
                return this.sysUserMapper.selectByPrimaryKey(id);
            }
        }
        return null;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public SysUser login(String username, String password) {
//        把用户输入的密码进行md5加密
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(username);
        criteria.andPwdEqualTo(pwd);
        List<SysUser> sysUsers = this.sysUserMapper.selectByExample(example);
//        这个判断一定要加上，因为数据库中可能没有查到相关的信息
        if(sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        }

        return null;
    }
}
