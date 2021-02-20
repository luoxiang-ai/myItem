package com.java.carrent.web.controller;

import com.java.carrent.common.vo.MenuVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.SysMenu;
import com.java.carrent.entity.SysUser;
import com.java.carrent.service.MenuService;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.TreeNode;
import com.java.carrent.utils.TreeNodeBuilder;
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
        if (user.getType() == SysConstast.USER_TYPE_SUPER) {
            list = this.menuService.queryAllMenuForList(menuVo);
        } else {
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }

        List<TreeNode> nodes = new ArrayList<>();

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
}
