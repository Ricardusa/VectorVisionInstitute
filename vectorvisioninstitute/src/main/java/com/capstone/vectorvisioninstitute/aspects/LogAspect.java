package com.capstone.vectorvisioninstitute.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;


/**
 * Aspect class for logging method execution and performance metrics.
 * Provides logger statements whenever an operation is invoked on the backend,
 * allowing tracking of execution time to identify potential performance issues.
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    /**
     * Logs method execution start, measures execution time, and logs method execution end.
     * @param joinPoint ProceedingJoinPoint representing the pointcut of the intercepted method.
     * @return The object returned by the intercepted method.
     * @throws Throwable Thrown if an exception occurs during method execution.
     */
    @Around("execution(* com.capstone.vectorvisioninstitute..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info(joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();
        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature().toString() + " method is : "+timeElapsed);
        log.info(joinPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    /**
     * Logs exceptions thrown during method execution.
     * @param joinPoint JoinPoint representing the pointcut of the intercepted method.
     * @param ex       The exception thrown during method execution.
     */
    @AfterThrowing(value = "execution(* com.capstone.vectorvisioninstitute.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }
}
