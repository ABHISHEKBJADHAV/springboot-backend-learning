package com.example.JobAppBackEnd.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    private Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("execution(* com.example.JobAppBackEnd.Service.JobService.*(..))")
    private Object measurePerformance(ProceedingJoinPoint jp)
    {
        long start = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = jp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        logger.info("Time taken by method - "+jp.getSignature().getName()+" is :"+ (end-start)+ "ms");
        return obj;
    }

}
