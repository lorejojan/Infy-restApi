package com.infy.infyinterns.utility;

import com.infy.infyinterns.exception.InfyInternException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Environment environment;

    @AfterThrowing(pointcut = "execution(* com.infy.infyinterns.service.ProjectAllocationServiceImpl.*(..))", throwing = "exception")
    public void logServiceException(InfyInternException exception) {
        logger.error(environment.getProperty(exception.getMessage()));
    }
}
