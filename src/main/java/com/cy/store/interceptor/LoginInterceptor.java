package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    //每次请求前，检测全局session中有没有登录了的uid，有才能放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //可以通过这个HttpServletRequest来访问session
        Object obj = request.getSession().getAttribute("uid");
        Object obj1 = request.getSession().getAttribute("aid");
        if (obj == null && obj1==null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
