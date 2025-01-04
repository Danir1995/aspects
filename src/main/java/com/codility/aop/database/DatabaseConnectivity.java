package com.codility.aop.database;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectivity {
    public void save(Object record) {
        System.out.println("Saving record: " + record);
    }
}
