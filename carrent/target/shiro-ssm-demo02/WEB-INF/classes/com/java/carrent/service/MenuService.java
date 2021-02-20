package com.java.carrent.service;

import com.java.carrent.common.vo.MenuVo;
import com.java.carrent.entity.SysMenu;
import com.java.carrent.utils.DataGridView;

import java.util.List;

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

    public abstract DataGridView queryAllMenu(MenuVo menuVo);
}
