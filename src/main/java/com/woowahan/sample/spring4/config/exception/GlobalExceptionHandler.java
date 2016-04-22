package com.woowahan.sample.spring4.config.exception;

import com.woowahan.sample.spring4.common.constant.ApiStatus;
import com.woowahan.sample.spring4.domain.ApiRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 공통 에러를 처리한다.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiRes handleGlobalException(RuntimeException e, HttpServletRequest request) {

        log.error("reqId={},uri={}", request.getAttribute("reqId"), request.getRequestURI(), e);

        return new ApiRes.Builder(ApiStatus.error, SystemErr.UNKNOWN_ERROR.code(), SystemErr.UNKNOWN_ERROR.message()).build();
    }

    @ExceptionHandler({ProjectException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiRes handleProjectException(ProjectException e, HttpServletRequest request){

        log.error("reqId={},uri={}, detail={}", request.getAttribute("reqId"), request.getRequestURI(), e.getLogMsg(), e);

        return new ApiRes.Builder(ApiStatus.error, e.getCode(), e.getMsg()).build();
    }

}
