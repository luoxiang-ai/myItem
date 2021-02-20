package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.entity.SysMenu;
import com.java.teaching.service.MenuService;
import com.java.teaching.common.vo.MenuVo;
import com.java.teaching.mapper.SysMenuMapper;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        return this.sysMenuMapper.queryMenuByUid(menuVo.getAvailable(), userId);
    }

    /**
     * 添加菜单
     *
     * @param menuVo
     */
    @Override
    public void addMenu(MenuVo menuVo) {
        this.sysMenuMapper.insertSelective(menuVo);
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        List<SysMenu> data = this.sysMenuMapper.queryAllMenu(menuVo);

        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 修改菜单
     *
     * @param menuVo
     */
    @Override
    public void updateMenu(MenuVo menuVo) {
        this.sysMenuMapper.updateByPrimaryKeySelective(menuVo);
    }

    /**
     * 修改菜单
     *
     * @param menuVo
     */
    @Override
    public void deleteMenu(MenuVo menuVo) {
//        删除菜单表的数据
        this.sysMenuMapper.deleteByPrimaryKey(menuVo.getId());
//        根据菜单id删除sys_role_menu里面的数据
        this.sysMenuMapper.deleteRoleMenuByMid(menuVo.getId());
    }

    /**
     * 根据id判断当前菜单有没有子节点
     *
     * @param id
     * @return
     */
    @Override
    public Integer queryMenuByPid(Integer id) {
        return this.sysMenuMapper.queryMenuByPid(id);
    }

    /**
     * 根据用户id查询权限code
     *
     * @param userid
     * @return
     */
    @Override
    public Set<String> queryCodeByUserId(Integer userid) {
        Set<String> codeList = this.sysMenuMapper.queryCodeByUserId(userid);
        return codeList;
    }
}
