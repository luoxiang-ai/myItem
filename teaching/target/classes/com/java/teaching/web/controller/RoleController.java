package com.java.teaching.web.controller;

import com.java.teaching.entity.SysRole;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.common.vo.RoleVo;
import com.java.teaching.service.RoleService;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 加载角色列表，返回DataGridView
     * @param roleVo
     * @return
     */
    @RequestMapping(value = "/loadAllRole.action")
    public DataGridView loadAllRole(RoleVo roleVo) {
        return this.roleService.queryAllRole(roleVo);
    }

    /**
     * 模糊查询角色
     * @param roleVo
     * @return
     */
    @RequestMapping(value = "/fuzzyQueryRole.action")
    public DataGridView fuzzyQueryRole(RoleVo roleVo) {
        List<SysRole> sysRoleList = this.roleService.queryAllRoleForList(roleVo);
        return new DataGridView((long) sysRoleList.size(), sysRoleList);
    }

    /**
     * 添加角色
     * @param roleVo
     * @return
     */
    @PostMapping(value = "/addRole.action")
    public ResultObj addRole(RoleVo roleVo) {

        System.out.println(roleVo);
        try {
            this.roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     * @param roleVo
     * @return
     */
    @PostMapping(value = "/updateRole.action")
    public ResultObj updateRole(RoleVo roleVo) {

        try {
            this.roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     * @param roleVo
     * @return
     */
    @PostMapping(value = "/deleteRole.action")
    public ResultObj deleteRole(RoleVo roleVo) {

        try {
            this.roleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     * @param roleVo
     * @return
     */
    @PostMapping(value = "/deleteBatchRole.action")
    public ResultObj deleteBatchRole(RoleVo roleVo) {

        try {
            this.roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的json
     * @param roleid
     * @return
     */
    @PostMapping(value = "/initRoleMenuTreeJson.action")
    public DataGridView initRoleMenuTreeJson(Integer roleid){

        return this.roleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 保存角色和菜单的关系
     * @param roleVo
     * @return
     */
    @PostMapping(value = "/saveRoleMenu.action")
    public ResultObj saveRoleMenu(RoleVo roleVo) {
        try {
            this.roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }

    }
}
