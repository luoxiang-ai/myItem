package com.java.carrent.service;

import com.java.carrent.common.vo.RoleVo;
import com.java.carrent.entity.SysRole;
import com.java.carrent.utils.DataGridView;

import java.util.List;

/**
 * 角色Service
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    public abstract List<SysRole> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据用户id查询用户可用的角色
     * @param roleVo
     * @param userId
     * @return
     */
    public abstract List<SysRole> queryRoleByUserIdForList(RoleVo roleVo, Integer userId);

    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    public abstract DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加角色
     * @param roleVo
     */
    public abstract void addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     */
    public abstract void updateRole(RoleVo roleVo);

    /**
     * 删除角色
     * @param roleid
     */
    public abstract void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     * @param roleid
     */
    public abstract void deleteBatchRole(Integer[] roleid);

    /**
     * 加载角色管理分配菜单
     * @param roleid
     * @return
     */
    public abstract DataGridView initRoleMenuTreeJson(Integer roleid);

    /**
     * 保存用户和菜单的关系
     * @param roleVo
     */
    public abstract void saveRoleMenu(RoleVo roleVo);
}
