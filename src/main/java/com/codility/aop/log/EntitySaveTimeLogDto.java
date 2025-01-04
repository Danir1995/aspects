package com.codility.aop.log;

import lombok.Data;

@Data
public class EntitySaveTimeLogDto {

    private String className;
    private Object entity;
    private long executionTimeMs;

    public EntitySaveTimeLogDto(String className, Object entity, long executionTimeMs) {
        this.className = className;
        this.entity = entity;
        this.executionTimeMs = executionTimeMs;
    }
}
