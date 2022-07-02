package com.songyuwong.infrastructure.api.starter.advice;

import com.songyuwong.infrastructure.api.starter.exception.ApiException;
import com.songyuwong.infrastructure.api.starter.response.Response;
import com.songyuwong.infrastructure.api.starter.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * HTTP 接口请求异常处理增强类
 * </p>
 *
 * @author SongYu
 * @since 2022/7/2
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class ExceptionAdvice {

    public static String API_STATER_PREFIX = "API STARTER >> ";

    public ExceptionAdvice() {
        log.info(API_STATER_PREFIX.concat("异常增强类初始化..."));
    }

    @ExceptionHandler(value = {Exception.class})
    public Response<Object> exception(Exception e) {
        log.error("捕获到未规范化异常 <<< {}", e.getMessage());
        e.printStackTrace();
        return Response.serverFailure(ResponseEnum.FAILURE_SERVER);
    }

    @ExceptionHandler(value = {ApiException.class})
    public Response<?> apiException(ApiException apiException) {
        log.error(apiException.getMessage());
        apiException.printStackTrace();
        return Response.callApiFailure(apiException);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Response<?> illegalArgException(IllegalArgumentException illegalArgumentException) {
        log.error("参数有误 {}",illegalArgumentException.getMessage());
        illegalArgumentException.printStackTrace();
        return Response.callApiFailure("参数有误",illegalArgumentException.getMessage());
    }

}
