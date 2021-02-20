package com.java.carrent.service;

import com.java.carrent.entity.SysMenu;
import com.java.carrent.common.vo.MenuVo;
import com.java.carrent.utils.DataGridView;

import java.util.List;
import java.util.Set;

/**
 * 菜单Service
 */
public interface MenuService {

    /**
     * 查询所有菜单
     * @param menuVo
     * @return
     */
    public abstract List<SysMenu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询用户可用的菜单
     * @param menuVo
     * @param userId
     * @return
     */
    public abstract List<SysMenu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId);

    /**
     * 添加菜单
     * @param menuVo
     */
    public abstract void addMenu(MenuVo menuVo);

    /**
     * 查询全部菜单
     * @param menuVo
     * @return
     */
    public abstract DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 修改菜单
     * @param menuVo
     */
    public abstract void updateMenu(MenuVo menuVo);

    /**
     * 修改菜单
     * @param menuVo
     */
    public abstract void deleteMenu(MenuVo menuVo);

    /**
     * 根据id判断当前菜单有没有子节点
     * @param id
     * @return
     */
    public abstract Integer queryMenuByPid(Integer id);

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
