package com.codility.aop.log;


import lombok.Data;

@Data
public class ExceptionLogDto {
    private String className;
    private String methodName;
    private Throwable exception;

    public ExceptionLogDto(String className, String methodName, Throwable exception) {
        this.className = className;
        this.methodName = methodName;
        this.exception = exception;
    }

}
