package com.project.ticketreservation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class MeasurementAspect {
    private static final Logger logger = LoggerFactory.getLogger(MeasurementAspect.class);

    @Around("execution(* com.project.ticketreservation.services..*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Method {} took {} ms to execute.", joinPoint.getSignature().getName(), (endTime - startTime));
        return result;
    }
}
