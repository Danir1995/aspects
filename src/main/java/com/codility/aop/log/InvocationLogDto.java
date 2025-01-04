package com.codility.aop.log;

import java.util.List;
public class InvocationLogDto {

    private String className;
    private String methodName;
    private List<Object> args;

    public InvocationLogDto(String className, String methodName, List<Object> args) {
        this.className = className;
        this.methodName = methodName;
        this.args = args;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
