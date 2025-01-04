package com.codility.aop.aspects;

import com.codility.aop.log.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {
    private final LogFacade logFacade;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect(LogFacade logFacade) {
        this.logFacade = logFacade;
    }

    // Logs the saving of entities in the DateRepository
    @After("execution(* com.codility.aop.repository.DateRepository.save(..))")
    public void logDateEntitySave(JoinPoint joinPoint) {
        logEntitySave(joinPoint);
    }

    // Logs the saving of entities in the MeetingRepository
    @After("execution(* com.codility.aop.repository.MeetingRepository.save(..))")
    public void logMeetingEntitySave(JoinPoint joinPoint) {
        logEntitySave(joinPoint);
    }

    // Logs the invocation of methods annotated with @Log
    @Before("@annotation(com.codility.aop.annotations.Log)")
    public void logMethodInvocation(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        logger.info("Method invoked: {}.{} with arguments: {}", className, methodName, args);

        InvocationLogDto logDto = new InvocationLogDto(className, methodName, args);
        logFacade.logInvocation(logDto);
    }

    // Logs the return value of the DateService.getNextDate method
    @AfterReturning(
            pointcut = "execution(* com.codility.aop.date.DateService.getNextDate(..))",
            returning = "returnValue"
    )
    public void logReturnValue(JoinPoint joinPoint, Object returnValue) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        if (returnValue != null) {
            logger.info("Method returned: {}.{} with value: {}", className, methodName, returnValue.getClass().getSimpleName());
            ReturnLogDto returnLogDto = new ReturnLogDto(className, methodName, returnValue);
            logFacade.logReturn(returnLogDto);
        } else {
            logger.info("Method {}.{} returned null", className, methodName);
        }
    }


    // Logs exceptions thrown by the DateService.getNextDate method
    @AfterThrowing(
            pointcut = "execution(* com.codility.aop.date.DateService.getNextDate(..))",
            throwing = "exception"
    )
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.error("Exception thrown in {}.{}: {}", className, methodName, exception.getMessage());

        ExceptionLogDto exceptionLogDto = new ExceptionLogDto(className, methodName, exception.fillInStackTrace());
        logFacade.logThrownException(exceptionLogDto);
    }

    // Logs details of entity saving operations across all repository classes
    @After("execution(* com.codility.aop.repository..*.save(..))")
    public void logEntitySave(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object entity = joinPoint.getArgs()[0];

        logger.info("Entity saved in {}: {}", className, entity);

        EntitySaveLogDto logDto = new EntitySaveLogDto(className, entity);
        logFacade.logEntitySave(logDto);
    }

    // Logs the execution time of the DatabaseConnectivity.save method
    @Around("execution(* com.codility.aop.database.DatabaseConnectivity.save(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Start timing
        long startTime = System.currentTimeMillis();

        // Execute the method
        Object result = joinPoint.proceed();

        // End timing
        long endTime = System.currentTimeMillis();
        long executionTimeMs = endTime - startTime;

        // Collecting method details and entity being saved
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object entity = joinPoint.getArgs()[0];

        // Create a DTO to log the execution time
        EntitySaveTimeLogDto logDto = new EntitySaveTimeLogDto(className, entity, executionTimeMs);

        logger.info("Method {}.save executed in {} ms. Entity: {}", className, executionTimeMs, entity);

        // Log the execution time
        logFacade.logEntitySavingTime(logDto);

        // Return the method's original result
        return result;
    }
}
