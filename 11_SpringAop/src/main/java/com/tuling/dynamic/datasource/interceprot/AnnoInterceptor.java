package com.tuling.dynamic.datasource.interceprot;

import com.ryan.test.LoginRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuzongshuai
 * @date 2023/1/28 20:06
 */
public class AnnoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器了");
        // 反射获取方法上的LoginRequred注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        if(loginRequired==null){
            return true;
        }
        // 有LoginRequired注解说明需要登录，提示用户登录
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print("你访问的资源需要登录");

        return false;
    }
}
