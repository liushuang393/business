package org.com.business.spring.boot.fw.core.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.utils.BZBeanUtils;
import org.com.business.spring.boot.fw.base.web.rest.MyWebSocket;
import org.com.business.spring.boot.fw.core.auth.model.CustomUserDetails;
import org.com.business.spring.boot.fw.core.auth.model.UserLoginForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {
        //do some logic here if you want something to be done whenever
        //the user successfully logs in.

        HttpSession session = httpServletRequest.getSession();
        CustomUserDetails authUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserLoginForm userLoginForm = (UserLoginForm) session.getAttribute(BZConstants.SESSION_FORM_NAME);
        BZBeanUtils.copyBeanProperties(authUser, userLoginForm);

        //session.invalidate();
        userLoginForm.setUserId(authUser.getUsername());
        session.setAttribute(BZConstants.SESSION_FORM_NAME, userLoginForm);
		// session.setAttribute("authorities", authentication.getAuthorities());
        MyWebSocket.httpSession = session;

        Object[] c = authentication.getAuthorities().stream().toArray();
        for (Object o : c) {
            log.info(o.toString());
        }

        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        // ログイン画面にリダイレクトする
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/menu");
    }
}