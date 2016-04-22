package com.woowahan.sample.spring4.config.exception;

import com.woowahan.sample.spring4.common.constant.ApiStatus;
import com.woowahan.sample.spring4.domain.ApiRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**.
 * input 파라미터와 관련된 Exception
 */
@Slf4j
@ControllerAdvice
public class ParamsExceptionHandler {

    /**
     * API로 요청이 온 파라미터를 Controller 파라미터로 bind하다가 실패 한 경우
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiRes handleBadRequestException(ServletRequestBindingException e, HttpServletRequest request) {

        log.error("reqId={},uri={},e={}", request.getAttribute("reqId"), request.getRequestURI(), e.getMessage());

        return new ApiRes.Builder(ApiStatus.error, HumanErr.INVALID_ARGS.code(), HumanErr.INVALID_ARGS.message() + e.getMessage()).build();
    }

    /**
     * 필수 파라미터로 지정한 파라미터가 없는 경우
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiRes handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {

        log.error("reqId={},uri={},e={}", request.getAttribute("reqId"), request.getRequestURI(), e.getMessage());

        return new ApiRes.Builder(ApiStatus.error, HumanErr.INVALID_ARGS.code(), "필수 파라미터가 없습니다.(" + e.getMessage() + ")").build();
    }

    /**
     * API로 요청한 파라미터들의 타입이 잘못 된 경우
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiRes handleTypeMismatchException(TypeMismatchException e, HttpServletRequest request) {

        log.error("reqId={},uri={},e={}", request.getAttribute("reqId"), request.getRequestURI(), e.getMessage());

        return new ApiRes.Builder(ApiStatus.error, HumanErr.INVALID_ARGS.code(), "파라미터 타입이 잘못되었습니다. (" + e.getMessage() + ")").build();
    }

}
