package com.springCourse.crudMvcAopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    //    setup logger
    private final Logger myLogger = Logger.getLogger(getClass().getName());

    //    setup pointcut declarations
    @Pointcut("execution(* com.springCourse.crudMvcAopdemo.controller.*.*(..))")
    public void forControllerPackage() {
    }

    //    do the same for service and dao
    @Pointcut("execution(* com.springCourse.crudMvcAopdemo.service.*.*(..))")
    public void forServicePackage() {
    }

    @Pointcut("execution(* com.springCourse.crudMvcAopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    //        Add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
//        Display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method: " + theMethod);

//        Display the arguments to the method

//        Get the arguments
        Object[] args = theJoinPoint.getArgs();
//        Loop thru and display args
        for (Object tempArg : args) {
            myLogger.info("======> argument: " + tempArg);

        }

    }

    //        Add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void afterReturning(JoinPoint theJoinpoint, Object theResult) {

//        Display the method we re returning from
        String theMethod = theJoinpoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturning: from method: " + theMethod);
//        Display data returned
        myLogger.info("=====> result: " + theResult);
    }


}
