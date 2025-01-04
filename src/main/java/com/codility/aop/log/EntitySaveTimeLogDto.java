package com.codility.aop.log;


public record EntitySaveTimeLogDto(String className, Object entity, long executionTimeMs) {}
