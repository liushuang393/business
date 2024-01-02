package org.com.business.spring.boot.fw.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandleAdvice
		extends
			ResponseEntityExceptionHandler {

	private static final String DEFAULT_ERROR_VIEW = "commonError";

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			@Nullable Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex,
					WebRequest.SCOPE_REQUEST);
		}
		logger.error(ex.getMessage(), ex);
		Map<String, String> respMsg = new HashMap<>();
		respMsg.put("errorMessage", ex.getMessage());
		return new ResponseEntity<>(respMsg, headers, status);
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handler(HttpServletRequest req, HttpServletResponse res,
			Exception e) {

		log.info("Request Exception...");

		log.info("Status:" + res.getStatus());
		log.info("url:" + req.getPathInfo());

		log.error(e.getMessage(), e);
		req.getSession().invalidate();

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e.getMessage());
		mav.addObject("url", req.getRequestURL());

		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}