package com.java.carrent.mapper;

import com.java.carrent.entity.SysMenu;
import com.java.carrent.entity.SysMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 查询所有菜单
     * @param sysMenu
     * @return
     */
    List<SysMenu> queryAllMenu(SysMenu sysMenu);

    /**
     *
     * @param id
     * @return
     */
    Integer queryMenuByPid(Integer id);

    /**
     * 删除sys_role_menu里面的数据
     * @param id
     */
    void deleteRoleMenuByMid(Integer id);

    /**
     * 根据角色id查询该角色所有的菜单
     * @param available
     * @param roleid
     */
    List<SysMenu> queryMenuByRoleId(@Param(value = "available") Integer available, @Param(value = "rid") Integer roleid);

    /**
     * 根据用户id查询该用户拥有的菜单
     * @param available
     * @param userId
     * @return
     */
    List<SysMenu> queryMenuByUid(@Param(value = "available") Integer available, @Param(value = "userid") Integer userId);

    /**
     * 根据用户id查询权限code
     * @param userid
     * @return
     */
    Set<String> queryCodeByUserId(Integer userid);

    /**
     * 查询全部权限code(超级管理员使用)
     * @return
     */
    Set<String> queryAllCode();
}