package com.java.teaching.interceptors;

import com.java.teaching.entity.SysUser;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求处理前....");
        String uri = request.getRequestURI();
        if(uri.indexOf("login") != -1) {
            return true;
        }

//        获取session
        HttpSession session = request.getSession();
//        获取用户信息
        SysUser user = (SysUser) session.getAttribute("sysUser");
        if(user != null) {
            return true;
        }

        request.setAttribute("msg", "你还没有登录");
        request.getRequestDispatcher("/WEB-INF/pages/system/main/login.jsp").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("请求处理中...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("请求处理后....");
    }
}
