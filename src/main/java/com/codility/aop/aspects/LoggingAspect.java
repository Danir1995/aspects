package com.codility.aop.aspects;

import com.codility.aop.log.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private final LogFacade logFacade;

    public LoggingAspect(LogFacade logFacade) {
        this.logFacade = logFacade;
    }
}
