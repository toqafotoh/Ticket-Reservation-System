package com.project.ticketreservation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Order(0)
@Component
public class MeasurementInfoAspect {
    private static final Logger logger = LoggerFactory.getLogger(MeasurementInfoAspect.class);

    @Before("execution(* com.project.ticketreservation.services..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Calling method: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(
        pointcut = "execution(* com.project.ticketreservation.services..*(..))",
        returning = "result"
    )
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(
        pointcut = "execution(* com.project.ticketreservation.services..*(..))",
        throwing = "ex"
    )
    public void logMethodException(JoinPoint joinPoint, Exception ex) {
        logger.error("Method {} threw exception: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
