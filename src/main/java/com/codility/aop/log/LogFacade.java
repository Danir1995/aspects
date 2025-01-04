package com.codility.aop.log;

import org.springframework.stereotype.Component;

@Component
public class LogFacade {

    // Logs the details of method invocation
    public void logInvocation(InvocationLogDto logDto) {
        System.out.println("Invocation Log: " + logDto);
    }

    // Logs the details of exceptions thrown during method execution
    public void logThrownException(ExceptionLogDto exceptionLogDto) {
        System.out.println("Exception Log: " + exceptionLogDto);
    }

    // Logs the time taken to save an entity in the database
    public void logEntitySavingTime(EntitySaveTimeLogDto timeLogDto) {
        System.out.println("Entity Save Time Log: " + timeLogDto);
    }

    // Logs the return value of a method
    public void logReturn(ReturnLogDto returnLogDto) {
        System.out.println("Logged return value: " + returnLogDto);
    }

    // Logs details of saved entities
    public void logEntitySave(EntitySaveLogDto logDto) {
        System.out.println("Logged entity save operation: " + logDto);
    }
}
