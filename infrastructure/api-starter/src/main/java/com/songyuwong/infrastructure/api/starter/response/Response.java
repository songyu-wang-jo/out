package com.songyuwong.infrastructure.api.starter.response;

import com.songyuwong.infrastructure.api.starter.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 统一响应
 * </p>
 *
 * @author SongYu
 * @since 2022/7/2
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T data;

    public static Response<Object> serverFailure(ResponseEnum responseEnum) {
        return Response
                .builder()
                .code(responseEnum.getCode())
                .message(responseEnum.getMessage())
                .data(null)
                .build();
    }

    public static Response<?> callApiFailure(ApiException apiException) {
        return Response
                .builder()
                .code(ResponseEnum.FAILURE_API.getCode())
                .message(apiException.getMessage())
                .data(apiException.getData())
                .build();
    }

    public static Response<?> callApiFailure(String detail, Object data) {
        return Response
                .builder()
                .code(ResponseEnum.FAILURE_API.getCode())
                .message(ResponseEnum.FAILURE_API.getMessage().concat(detail))
                .data(data)
                .build();
    }

}
