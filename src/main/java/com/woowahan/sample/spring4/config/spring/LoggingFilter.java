package com.woowahan.sample.spring4.config.spring;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * LoggingFilter는 API의 Request와 Response를 로깅하기 위해서 Servlet 자체 필터를 Override 해서 사용하는 방법을 이용한다.
 * (Interceptor에서도 할 수 있는데 딱 로깅만이면 요기서 해도 무방함)
 */
@Slf4j
class LoggingFilter extends AbstractRequestLoggingFilter {

    private final AtomicLong id = new AtomicLong(1);


    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        if (!Strings.isNullOrEmpty(request.getQueryString()))
            log.info(message);
    }


    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        long reqId = id.incrementAndGet();

        RequestLoggingWrapper requestLoggingWrapper = new RequestLoggingWrapper(reqId, request);
        long startTime = requestLoggingWrapper.getStartTime();

        ResponseLoggingWrapper responseLoggingWrapper = new ResponseLoggingWrapper(reqId, startTime, response);

        super.doFilterInternal(requestLoggingWrapper, responseLoggingWrapper, filterChain);
    }

}
