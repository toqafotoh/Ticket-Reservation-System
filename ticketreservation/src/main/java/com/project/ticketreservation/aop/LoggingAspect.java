package com.project.ticketreservation.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Order(2)
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut(value = "execution(* com.project.ticketreservation.repositories..*(..))")
    public void forRepositoryLog(){}

    @Pointcut(value = "execution(* com.project.ticketreservation.services..*(..))")
    public void forServiceLog(){}

    @Pointcut(value = "execution(* com.project.ticketreservation.controllers..*(..))")
    public void forControllerLog(){}

    @Pointcut(value = "forControllerLog() || forServiceLog() || forRepositoryLog()")
    public void forAllApp(){}

    @Before(value = "forAllApp()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();

        log.info("====> Method Name is >> {}", methodName);

        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            log.info(("===> argument >> {}"), arg);
        }
    }

}
