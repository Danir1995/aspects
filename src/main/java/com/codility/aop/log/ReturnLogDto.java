package com.codility.aop.log;

public record ReturnLogDto(String className, String methodName, Object returnValue) {
}
