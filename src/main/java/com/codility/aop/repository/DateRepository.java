package com.codility.aop.repository;

import com.codility.aop.date.DateDto;
import org.springframework.stereotype.Repository;

@Repository
public class DateRepository {
    public void save(DateDto dateDto){
        System.out.println("Saving date: " + dateDto);
    }
}
