package com.improving.bootcamp.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class LoggingInterceptor implements HandlerInterceptor {

    public static final String FIRST_REQUEST_TIME = "firstRequestTime";
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if(session.getAttribute(FIRST_REQUEST_TIME) == null) {
            session.setAttribute(FIRST_REQUEST_TIME, LocalDateTime.now());
        }

        logger.info("Request Received {} {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session = request.getSession();

        logger.info("Completed Request for session that started at {}", session.getAttribute(FIRST_REQUEST_TIME));
    }
}
