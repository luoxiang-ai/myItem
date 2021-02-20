package com.java.teaching.mapper;

import com.java.teaching.common.vo.RoleVo;
import com.java.teaching.entity.SysRole;
import com.java.teaching.entity.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 根据角色id删除该角色相关联的菜单
     * @param roleid
     */
    void deleteRoleMenuByRid(Integer roleid);

    /**
     * 保存该角色和菜单之间的关系
     * @param roleid
     * @param mid
     */
    void insertRoleMenu(@Param(value = "rid") Integer roleid, @Param(value = "mid") Integer mid);

    /**
     * 查询全部角色，模糊查询
     * @param roleVo
     * @return
     */
    List<SysRole> queryAllRole(RoleVo roleVo);
}