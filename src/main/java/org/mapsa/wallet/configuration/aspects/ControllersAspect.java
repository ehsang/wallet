package org.mapsa.wallet.configuration.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.mapsa.wallet.repositories.LogModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//The annotations @Aspect, @Component, and @Slf4j are
//commonly used in Spring applications for aspect-oriented programming (AOP) and logging.
@Aspect
@Component
@Slf4j
public class ControllersAspect {
    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LogModelRepository logModelRepository;

    @Before("within(org.mapsa.wallet.controllers.AbstractController+)")
    public void before(){

    }
}
