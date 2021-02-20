package com.java.carrent.utils;

import com.java.carrent.entity.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 解耦的方式得到web作用域
 */
public class WebUtils {

    /**
     * 得到request
     */
    public static HttpServletRequest getCurrentServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }
    /**
     * 得到session
     */
    public static HttpSession getCurrentSession() {
        return getCurrentServletRequest().getSession();
    }

    /**
     * 得到sesison里面的用户
     */
    public static SysUser getCurrentUser() {
        SysUser user=(SysUser) getCurrentSession().getAttribute("sysUser");
        return user;
    }
    /**
     * 得到sesison里面的用户
     */
    public static String getCurrentUserName() {
        SysUser user=(SysUser) getCurrentSession().getAttribute("sysUser");
        return user.getRealname();
    }
}
