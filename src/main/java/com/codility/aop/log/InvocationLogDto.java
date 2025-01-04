package com.codility.aop.log;

import lombok.Data;
import java.util.List;

@Data
public class InvocationLogDto {

    private String className;
    private String methodName;
    private List<Object> args;

    public InvocationLogDto(String className, String methodName, List<Object> args) {
        this.className = className;
        this.methodName = methodName;
        this.args = args;
    }
}
