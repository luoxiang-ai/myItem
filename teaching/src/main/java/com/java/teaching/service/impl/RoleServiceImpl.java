package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.RoleVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.SysMenu;
import com.java.teaching.entity.SysMenuExample;
import com.java.teaching.entity.SysRole;
import com.java.teaching.entity.SysRoleExample;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.TreeNode;
import com.java.teaching.mapper.SysMenuMapper;
import com.java.teaching.mapper.SysRoleMapper;
import com.java.teaching.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询所有角色
     *
     * @param roleVo
     * @return
     */
    @Override
    public List<SysRole> queryAllRoleForList(RoleVo roleVo) {
        return this.sysRoleMapper.queryAllRole(roleVo);
    }

    /**
     * 根据用户id查询用户可用的角色
     *
     * @param roleVo
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return null;
    }

    /**
     * 查询所有角色
     *
     * @param roleVo
     * @return
     */
    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<SysRole> roleList = this.sysRoleMapper.selectByExample(new SysRoleExample());
        return new DataGridView(page.getTotal(), roleList);
    }

    /**
     * 添加角色
     *
     * @param roleVo
     */
    @Override
    public void addRole(RoleVo roleVo) {
        this.sysRoleMapper.insertSelective(roleVo);
    }

    /**
     * 修改角色
     *
     * @param roleVo
     */
    @Override
    public void updateRole(RoleVo roleVo) {
//        按主键更新不为null的字段
        this.sysRoleMapper.updateByPrimaryKeySelective(roleVo);
    }

    /**
     * 删除角色
     *
     * @param roleid
     */
    @Override
    public void deleteRole(Integer roleid) {
//        按主键删除角色
        this.sysRoleMapper.deleteByPrimaryKey(roleid);
    }

    /**
     * 批量删除角色
     *
     * @param roleid
     */
    @Override
    public void deleteBatchRole(Integer[] roleid) {
//        遍历数组
        for (Integer roleId : roleid) {
//            调用按主键删除角色的方法
            this.deleteRole(roleId);
        }
    }

    /**
     * 加载角色管理分配菜单
     *
     * @param roleid
     * @return
     */
    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
//        1.查询所有菜单
        List<SysMenu> allMenu = this.sysMenuMapper.selectByExample(new SysMenuExample());
//        2.根据角色id查询该角色所所有的菜单
        List<SysMenu> roleMenu = this.sysMenuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE, roleid);

        List<TreeNode> data = new ArrayList<>();
        for (SysMenu menu1 : allMenu) {
            String checkArr = String.valueOf(SysConstast.CODE_ZERO);
            for (SysMenu menu2 : roleMenu) {
                if (menu1.getId() == menu2.getId()) {
                    checkArr = String.valueOf(SysConstast.CODE_ONE);
                    break;
                }
            }

            Integer id = menu1.getId();
            Integer pid = menu1.getPid();
            String title = menu1.getTitle();
            Boolean spread = menu1.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id, pid, title, spread, checkArr));
        }

        return new DataGridView((long) data.size(), data);
    }

    /**
     * 保存用户和菜单的关系
     *
     * @param roleVo
     */
    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer roleid = roleVo.getRoleid();
        Integer[] mids = roleVo.getIds();
//        根据rid删除sys_role_menu中的数据
        this.sysRoleMapper.deleteRoleMenuByRid(roleid);
//        保存角色和菜单的关系
        for (Integer mid : mids) {
            this.sysRoleMapper.insertRoleMenu(roleid, mid);
        }

    }
}
