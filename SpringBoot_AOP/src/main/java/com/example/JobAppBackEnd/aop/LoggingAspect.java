package com.example.JobAppBackEnd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Before(), After(), Around() these are advices
    // execution() method args: return_type, class-name.method-name,

//  @Before("execution(* com.example.JobAppBackEnd.Service.JobService.getJobPost(..)) || execution(* com.example.JobAppBackEnd.Service.JobService.getJobPosts(..))")
//  @Before("execution(* com.example.JobAppBackEnd.Service.JobService.getJobPost*(..))")
    @Before("execution(* com.example.JobAppBackEnd.Service.JobService.getJobPost(..))")
    public void logMethodCall()
    {
        logger.info("Method called");
    }

    @Before("execution(* com.example.JobAppBackEnd.Service.JobService.*(..))")
    public void logMethodBeforeExecuted(JoinPoint jp)
    {
        logger.info("Method called:" + jp.getSignature().getName());
    }

    // Only @After works after returning and after throwing as well just like finally block of try catch.
    @After("execution(* com.example.JobAppBackEnd.Service.JobService.*(..))")
    public void logMethodAfterExecuted(JoinPoint jp)
    {
        logger.info("Method executed:" + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.example.JobAppBackEnd.Service.JobService.*(..))")
    public void logMethodAfterThrowing(JoinPoint jp)
    {
        logger.info("Method has some issue:" + jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.JobAppBackEnd.Service.JobService.*(..))")
    public void logMethodAfterReturning(JoinPoint jp)
    {
        logger.info("Method executed successfully:" + jp.getSignature().getName());
    }

}
