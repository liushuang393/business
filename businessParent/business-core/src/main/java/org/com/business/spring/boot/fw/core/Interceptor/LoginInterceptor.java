package org.com.business.spring.boot.fw.core.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.com.business.spring.boot.common.BZConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value(value = "${spring.profiles.active}")
    private String profile;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        log.debug("profile: " + profile);
        if (BZConstants.PROFILES_ACTIVE_DEV.equals(profile)) {
            return true;
        }
        //        if (BZConstants.PROFILES_ACTIVE_DEV.equals(profile)) {
        //            return true;
        //        }
        String url = request.getRequestURI();
        log.debug("リクエストURL:" + url);
        if (url.matches("/$") || url.indexOf("/welcome") >= 0
                || url.indexOf("/login") >= 0
                || url.indexOf("/error") >= 0
                || url.indexOf("/commonError") >= 0
                || url.indexOf("/changeLocale") >= 0
                || url.indexOf("/logout") >= 0) {
            return true;
        }

        HttpSession session = request.getSession();

        Object username = session.getAttribute(BZConstants.SESSION_FORM_NAME);

        if (username != null) {
            return true;
        }
        request.getRequestDispatcher("/welcome").forward(request, response);

        return false;
    }
}