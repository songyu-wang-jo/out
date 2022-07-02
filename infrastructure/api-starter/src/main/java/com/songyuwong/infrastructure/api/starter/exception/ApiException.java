package com.songyuwong.infrastructure.api.starter.exception;


import lombok.*;

/**
 * <p>
 * 接口调用异常
 * </p>
 *
 * @author SongYu
 * @since 2022/7/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {

    private static final String API_EXCEPTION_PREFIX = "接口调用异常 << ";

    /**
     * 异常相关数据
     */
    private Object data;

    public ApiException() {
        super(API_EXCEPTION_PREFIX.concat(">>"));
    }

    public ApiException(String message) {
        super(API_EXCEPTION_PREFIX.concat(message));
    }

    public ApiException(String message, Throwable cause) {
        super(API_EXCEPTION_PREFIX.concat(message), cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(API_EXCEPTION_PREFIX.concat(message), cause, enableSuppression, writableStackTrace);
    }

    public static void throwApiException(String message) {
        ApiException apiException = new ApiException(message);
        apiException.setData(null);
        throw apiException;
    }

    public static void throwIllegalArgumentException(String message, Object data) {
        ApiException apiException = new ApiException(message);
        apiException.setData(data);
        throw apiException;
    }

    public static void throwIllegalArgumentException(String message, Object data, Throwable cause) {
        ApiException apiException = new ApiException(message, cause);
        apiException.setData(data);
        throw apiException;
    }

    public static void throwIllegalArgumentException(String message, Object data, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        ApiException apiException = new ApiException(message, cause, enableSuppression, writableStackTrace);
        apiException.setData(data);
        throw apiException;
    }

}
