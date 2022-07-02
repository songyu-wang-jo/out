package com.songyuwong.infrastructure.api.starter.response;

/**
 * <p>
 * 响应枚举
 * </p>
 *
 * @author SongYu
 * @since 2022/7/2
 */
public enum ResponseEnum {

    // 成功的响应
    SUCCESS_RESP(2000, "成功 "),


    // 失败的响应
    FAILURE_RESP(4000, "失败 "),
    FAILURE_SERVER(5000, "服务不可用，努力修复中... "),
    FAILURE_API(4100, "接口调用失败 ")
    ;

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
