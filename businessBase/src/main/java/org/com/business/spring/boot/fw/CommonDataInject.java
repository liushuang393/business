package org.com.business.spring.boot.fw;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class CommonDataInject {

    @Pointcut("execution(* org.com.business.spring.boot..*.mapper.*Mapper.insert*(..))")
    private void insertCutMethod() {
    }

    @Pointcut("execution(* org.com.business.spring.boot..*.mapper.*Mapper.update*(..))")
    private void updateCutMethod() {
    }

    @Around("insertCutMethod()")
    public Object doInsertAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.debug("[insert]"+arg);
        }
        Object o = pjp.proceed();
        return o;
    }

    @Around("updateCutMethod()")
    public Object doupdateAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.debug("[update]"+arg);
		}
        Object o = pjp.proceed();
        return o;
    }
}