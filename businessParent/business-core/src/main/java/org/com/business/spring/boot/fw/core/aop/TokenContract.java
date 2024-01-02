package org.com.business.spring.boot.fw.core.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.core.annotation.Token;
import org.com.business.spring.boot.fw.core.exception.FormRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Order(20)
public class TokenContract {

    @Value(value = "${spring.profiles.active}")
    private String profile;

    @Autowired
    HttpServletRequest request = null;
    @Autowired
    HttpServletResponse response = null;

    //    ThreadLocal<Long> startTime = new ThreadLocal<>();
    //
    //    @Pointcut("bean(*Controller)")
    //    public void controllerPoincut() {
    //    }
    //
    //    @Pointcut("bean(*Service)")
    //    public void servicePoincut() {
    //    }

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(token)")
    public void checkToken(final JoinPoint joinPoint, Token token) {

        try {

            log.debug("profile: " + profile);//開発環境（dev）場合、利用しない。
            if (BZConstants.PROFILES_ACTIVE_DEV.equals(profile)) {
                return;
            }

            if (token != null) {
                //joinPoint中のすべてパラメータを取得

                boolean needSaveSession = token.save();
                if (needSaveSession) {
                    creatTokenValue();
                }

                boolean needRemoveSession = token.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit()) {
                        log.error("フォームが二度提出しました。");
                        throw new FormRepeatException("フォームが二度提出しました。");
                    }
                    request.getSession().removeAttribute(BZConstants.BZ_TOKEN);
                    creatTokenValue();
                }
            }

        } catch (FormRepeatException e) {

            throw e;
        } catch (Exception e) {
            log.error("token異常が発生しました。　" + e);
            throw e;
        }
    }

    private boolean isRepeatSubmit() throws FormRepeatException {
        String serverToken = (String) request.getSession(false).getAttribute(BZConstants.BZ_TOKEN);
        String clinetToken = request.getParameter(BZConstants.BZ_TOKEN);
        log.debug("リクエストからToken値：" + clinetToken + ",Session中Token値：" + serverToken);
        if (StringUtils.isEmpty(serverToken) || StringUtils.isEmpty(clinetToken)
                || !clinetToken.equals(serverToken)) {
            return true;
        } else {
            return false;
        }
    }

    private void creatTokenValue() {
        String uuid = UUID.randomUUID().toString();
        request.getSession().setAttribute(BZConstants.BZ_TOKEN, uuid);
        log.debug("画面表示Token値" + uuid);
        return;
    }
}