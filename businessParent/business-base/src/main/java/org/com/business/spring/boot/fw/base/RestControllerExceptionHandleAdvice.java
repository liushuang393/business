package org.com.business.spring.boot.fw.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandleAdvice {

    private static final String DEFAULT_ERROR_VIEW = "commonError";

    private static final String ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handler(HttpServletRequest req, HttpServletResponse res, Exception e) {

        log.info("Rest Request Exception...");

        log.info("Status:" + res.getStatus());
        log.info("url:" + req.getPathInfo());

        log.error(e.getMessage(), e);
        req.getSession().invalidate();

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @RequestMapping(value = "/commonError", method = RequestMethod.GET)
    public String commonError(Model model) {
        return DEFAULT_ERROR_VIEW;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(Model model) {
        return ERROR_VIEW;
    }
}