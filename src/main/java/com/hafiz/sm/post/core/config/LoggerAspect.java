package com.hafiz.sm.post.core.config;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
@Configuration
@RequiredArgsConstructor
public class LoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @AfterThrowing(pointcut = "execution(* com.hafiz.sm.post.*.*.*(..))", throwing = "ex")
    public void logBasePackageError(Exception ex) {
        logException(ex);
    }

    private void logException(Exception ex) {
        log.error("CLASS NAME: " + ex.getClass().getCanonicalName());
        log.error("CAUSE: " + ex.getCause());
        log.error("MESSAGE: " + ex.getMessage());

        Arrays.stream(ex.getStackTrace())
                .filter(e -> e.getClassName().startsWith("com.hafiz.sm"))
                .map(StackTraceElement::toString)
                .forEach(log::error);
    }

}