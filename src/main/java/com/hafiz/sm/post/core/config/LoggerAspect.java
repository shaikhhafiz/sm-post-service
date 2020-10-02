package com.hafiz.sm.post.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor
public class LoggerAspect {

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