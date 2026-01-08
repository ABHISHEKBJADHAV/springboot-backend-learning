package com.example.JobAppBackEnd.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.example.JobAppBackEnd.Service.JobService.getJobPost(..)) && args(postId1)")
    private Object validateParam(ProceedingJoinPoint jp, int postId1) throws Throwable {
        if(postId1<0)
        {
            LOGGER.info("postId1 is negative, updating it");
            postId1=-postId1;
            LOGGER.info("new Value "+postId1);
        }
        Object obj = jp.proceed(new Object[]{postId1});
        return obj;
    }
}
