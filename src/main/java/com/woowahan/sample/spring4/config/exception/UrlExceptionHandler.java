package com.woowahan.sample.spring4.config.exception;

import com.woowahan.sample.spring4.common.constant.ApiStatus;
import com.woowahan.sample.spring4.domain.ApiRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Url과 관련된 Exception
 */
@Slf4j
@ControllerAdvice
public class UrlExceptionHandler {

    /**
     * API 정의한 Method와 요청을 준 Method가 다른 경우
     *
     * @param e       HttpRequestMethodNotSupportedException
     * @param request HttpServletRequest
     * @return ApiRes
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ApiRes handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {

        log.warn("reqId={},uri={},method={},e={}", request.getAttribute("reqId"), request.getRequestURI(), request.getMethod(), e.getMessage());

        return new ApiRes.Builder(ApiStatus.error, HumanErr.NO_SUPPORT.code(), "해당 API는 " + request.getMethod() + "를 지원하지 않습니다.").build();
    }

    /**
     * 존재하지 않는 URL(API)를 요청한 경우 -> 이걸 쓰려면 ApiAppConfigInitializer customizeRegistration method에 설정을 해줘야 함.
     *
     * @param e NoHandlerFoundException
     * @return ApiRes
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiRes handleNoHandlerFoundException(NoHandlerFoundException e) {

        log.warn("uri={},method={}", e.getRequestURL(), e.getHttpMethod());

        return new ApiRes.Builder(ApiStatus.error, HumanErr.NO_SUPPORT.code(), "API가 존재하지 않습니다.").build();
    }


}
