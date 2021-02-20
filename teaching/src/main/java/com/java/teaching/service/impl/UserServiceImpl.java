package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.service.UserService;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.*;
import com.java.teaching.mapper.SysRoleMapper;
import com.java.teaching.mapper.SysRoleUserMapper;
import com.java.teaching.mapper.SysUserMapper;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.File;
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
    public void addUser(UserVo userVo) {
       if (userVo != null) {
//           设置密码初始密码
           userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.DEFAULT_PWD.getBytes()));
//           设置默认头像地址
           userVo.setUserface(SysConstast.DEFAULT_DIRECTORY + File.separator + SysConstast.DEFAULT_USER_FACE_IMG);
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
     * @param userVo
     */
    @Override
    public void resetUserPwd(UserVo userVo) {
        // 重置密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.DEFAULT_PWD.getBytes()));
        this.sysUserMapper.updateByPrimaryKeySelective(userVo);
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
    public SysUser queryUserByUserId(Integer userId) {
        if(userId != null && userId > 0) {
            return this.sysUserMapper.selectByPrimaryKey(userId);
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

    /**
     * 加载全部学生
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadAllStudent(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        userVo.setType(SysConstast.STUDENT_FLAG);
        userVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<UserVo> list = this.sysUserMapper.queryAllStudent(userVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 加载全部老师
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadAllTeacher(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        userVo.setType(SysConstast.STUDENT_FLAG);
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(SysConstast.TEACHER_FLAG);
        criteria.andAvailableEqualTo(SysConstast.CODE_ONE);
        List<SysUser> list = this.sysUserMapper.selectByExample(example);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据班级id查询学生
     *
     * @param classId
     * @return
     */
    @Override
    public List<SysUser> loadStudentByClassId(String classId) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andClassidEqualTo(classId);

        return this.sysUserMapper.selectByExample(example);
    }

    /**
     * 修改密码
     *
     * @param userVo
     */
    @Override
    public void updatePwd(UserVo userVo) throws Exception {
//        将新密码进行加密
        String newPwd = DigestUtils.md5DigestAsHex(userVo.getNewPwd().getBytes());
//        在实例类中设置新密码
        userVo.setPwd(newPwd);
//        更新密码
        this.sysUserMapper.updateByPrimaryKeySelective(userVo);
    }

    /**
     * 模糊查询学生
     *
     * @param userVo
     * @return
     */
    @Override
    public List<UserVo> fuzzyQueryStudent(UserVo userVo) {

        return this.sysUserMapper.fuzzyQueryStudent(userVo);
    }

    /**
     * 验证旧密码
     *
     * @param userVo
     * @return
     */
    @Override
    public int queryUserByUserIdAndPassword(UserVo userVo) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userVo.getUserid());
        criteria.andPwdEqualTo(DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes()));
        List<SysUser> list = this.sysUserMapper.selectByExample(example);

        return list.size();
    }

    /**
     * 根据课程id查询课程报名学生信息
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadStudentNumByCourseId(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<UserVo> list = this.sysUserMapper.loadStudentNumByCourseId(userVo);
        return new DataGridView(page.getTotal(), list);
    }
}
