package com.java.teaching.web.controller;

import com.java.teaching.common.vo.MenuVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.SysMenu;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.MenuService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import com.java.teaching.utils.TreeNode;
import com.java.teaching.utils.TreeNodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单控制器
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/loadIndexLeftMenuJson.action")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo, HttpSession session) {

//        得到当前登录的用户对象
        SysUser user = (SysUser) session.getAttribute("sysUser");
        List<SysMenu> list = null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        // 判断当前用户是否是超级管理员
        if (user.getType() == SysConstast.USER_TYPE_SUPER) {
//            如果是超级管理员，就查询所有的菜单
            list = this.menuService.queryAllMenuForList(menuVo);
        } else {
//            如果不是，就按用户id查询
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }

        List<TreeNode> nodes = new ArrayList<>();

        for (SysMenu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }

        return TreeNodeBuilder.builder(nodes, 1);
    }


    /**
     * 加载菜单管理左边的树
     * @param menuVo
     * @return
     */
    @PostMapping(value = "/loadMenuManagerLeftTreeJson.action")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo) {

        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        List<SysMenu> list=this.menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes= new ArrayList<>();
        //把list里面的数据放到nodes
        for (SysMenu menu : list) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            Boolean spread=menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target=menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return new DataGridView(nodes);
    }

    /**
     * 加载菜单列表返回DataGridView
     * @param menuVo
     * @return
     */
    @GetMapping(value = "/loadAllMenu.action")
    public DataGridView loadAllMenu(MenuVo menuVo) {

        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/addMenu.action")
    public ResultObj addMenu(MenuVo menuVo) {
        try {
            this.menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/updateMenu.action")
    public ResultObj updateMenu(MenuVo menuVo) {
        try {
            this.menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除菜单
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/deleteMenu.action")
    public ResultObj deleteMenu(MenuVo menuVo) {
        try {
            this.menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/checkMenuHasChildren.action")
    public ResultObj checkMenuHasChildren(MenuVo menuVo) {
//        根据pid查询菜单数量
        Integer count = this.menuService.queryMenuByPid(menuVo.getId());
        if (count > 0) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }
}
