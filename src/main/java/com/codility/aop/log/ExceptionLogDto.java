package com.codility.aop.log;

public record ExceptionLogDto(String className, String methodName, Object Exception) {}
