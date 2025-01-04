package com.codility.aop.log;

import java.util.List;

public record InvocationLogDto(String className, String methodName, List<Object> args) {}
