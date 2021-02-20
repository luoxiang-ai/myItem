package com.java.carrent.mapper;

import com.java.carrent.common.vo.MenuVo;
import com.java.carrent.entity.SysMenu;
import com.java.carrent.entity.SysMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<SysMenu> queryAllMenu(MenuVo menuVo);

    /**
     * 查询所有菜单
     * @param sysMenu
     * @return
     */
    List<SysMenu> queryAllMenu(SysMenu sysMenu);
}