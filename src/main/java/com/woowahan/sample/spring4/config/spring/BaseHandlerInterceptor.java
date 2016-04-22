package com.woowahan.sample.spring4.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * API에서 기본적으로 사용하는 Interceptor
 * (Interceptor의 역할은 Controller 전후에 공통적으로 처리되어야 하는 로직을 한 곳에서 처리하기 위함.)
 */
@Slf4j
public class BaseHandlerInterceptor implements HandlerInterceptor {

    /**
     * Controller 시작 전
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * Controller 끝난 후 (Exception이 난 경우면 수행 안됨)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * Controller 끝난 후 (Exception이 났어도 수행)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
