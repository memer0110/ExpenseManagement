
package com.example.ExpenseManagement.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all methods in controllers and services package
    @Pointcut("execution(* com.example.ExpenseManagement.controllers..(..)) || execution( com.example.ExpenseManagement.services..*(..))")
    public void applicationMethods() {}

    // Before advice - Runs before method execution
    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing Method: {} with arguments: {}", joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()));
    }

    // After Returning advice - Runs after successful execution
    @AfterReturning(value = "applicationMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method Executed: {} | Return Value: {}", joinPoint.getSignature().toShortString(), result);
    }

    // After Throwing advice - Runs if method throws an exception
    @AfterThrowing(value = "applicationMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Method {} Threw Exception: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }

    // Around advice - Logs method execution time
    @Around("applicationMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception in {}: {}", joinPoint.getSignature().toShortString(), e.getMessage());
            throw e;
        }
        long end = System.currentTimeMillis();
        logger.info("Execution Time of {}: {} ms", joinPoint.getSignature().toShortString(), (end - start));
        return result;
    }
}
