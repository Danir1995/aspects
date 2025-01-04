package com.codility.aop.repository;

import com.codility.aop.log.MeetingDto;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingRepository {
    public void save(MeetingDto meetingDto) {
        System.out.println("Saving meeting: " + meetingDto);
    }
}
