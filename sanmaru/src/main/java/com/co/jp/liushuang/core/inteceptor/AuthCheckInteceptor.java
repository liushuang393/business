package com.co.jp.liushuang.core.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.co.jp.liushuang.common.Constants;
import com.co.jp.liushuang.controller.form.UserLoginForm;
import com.co.jp.liushuang.core.annotation.AuthCheckAnnotation;
import com.co.jp.liushuang.service.common.UserService;

public class AuthCheckInteceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HandlerMethod methodHandler = (HandlerMethod) handler;
        AuthCheckAnnotation auth = methodHandler.getMethodAnnotation(AuthCheckAnnotation.class);
        //如果方法中添加了@AuthCheckAnnotation 这里的auth不为null
        //如果@AuthCheckAnnotation(check=false) 这里的auth为false,即不用进行拦截验证，@AuthCheckAnnotation默认为前面定义的true　　
        if (auth != null && !auth.check()) {
            return true;
        }
        UserLoginForm user = (UserLoginForm) request.getSession().getAttribute(Constants.SESSION_FORM_NAME);
        try {
            //userService.getUserById(request, user);
            return true;
        } catch (Exception e) {
            //  request.getRequestDispatcher("loginInit.do").forward(request, response);
            return false;
        }
    }

}
