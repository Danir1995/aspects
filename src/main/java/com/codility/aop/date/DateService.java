package com.codility.aop.date;

import com.codility.aop.annotations.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;


@Service
public class DateService {
    private final Clock clock;
    private final DateRepository dateRepository;

    @Autowired
    DateService(Clock clock, DateRepository dateRepository) {
        this.clock = clock;
        this.dateRepository = dateRepository;
    }

    @Log
    public DateDto getNextDate() {
        DateDto dateDto = new DateDto(LocalDate.now(clock));
        dateRepository.save(dateDto);
        return dateDto;
    }

}
