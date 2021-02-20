package com.java.carrent.service.impl;

import com.java.carrent.common.vo.MenuVo;
import com.java.carrent.entity.SysMenu;
import com.java.carrent.mapper.SysMenuMapper;
import com.java.carrent.service.MenuService;
import com.java.carrent.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuService实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询所有菜单
     *
     * @param menuVo
     * @return
     */
    @Override
    public List<SysMenu> queryAllMenuForList(MenuVo menuVo) {
        return this.sysMenuMapper.queryAllMenu(menuVo);
    }

    /**
     * 根据用户id查询用户可用的菜单
     *
     * @param menuVo
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return this.sysMenuMapper.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     *
     * @param menuVo
     */
    @Override
    public void addMenu(MenuVo menuVo) {

    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        return null;
    }
}
