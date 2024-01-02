package org.com.business.spring.boot.fw.core.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.business.spring.boot.common.BZMessages;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Securityの認証失敗時に呼ばれるハンドラクラス
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationFailureHandler
	 * #onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException authenticationException) throws IOException, ServletException {

		String errorId = "";
		// ExceptionからエラーIDをセットする
		if (authenticationException instanceof BadCredentialsException) {
			errorId = BZMessages.MESSAGE_EU000001;
		}

		//httpServletRequest.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authenticationException);
		//httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + "/welcome?error=" + errorId)
		//		.forward(httpServletRequest, httpServletResponse);

		// ログイン画面にリダイレクトする
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/welcome?error=" + errorId);
		// ログイン画面にリダイレクトする
		//httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/welcome?error=" + errorId);
	}
}