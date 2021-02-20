package com.java.carrent.realm;

import com.java.carrent.entity.SysUser;
import com.java.carrent.service.MenuService;
import com.java.carrent.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 用户登录的realm
 */
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser)principalCollection.getPrimaryPrincipal();
        if (user != null && user.getUserid() != null) {
            Set<String> codeList = null;
//            如何用户是超级管理员，就查询全部权限code
            if(user.getType() == 1) {
                codeList = this.menuService.queryAllCode();
            } else {
                codeList = this.menuService.queryCodeByUserId(user.getUserid());
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addStringPermissions(codeList);
            return info;
        }

        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String) authenticationToken.getPrincipal();
        System.out.println(name);
        SysUser user = this.userService.queryUserByUsername(name);
        if(user != null) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(), getName());
            return info;
        }
        return null;
    }
}
