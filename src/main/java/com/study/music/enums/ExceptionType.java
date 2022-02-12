package com.study.music.enums;

public enum ExceptionType {
    INNER_ERROR(500, "系统内部错误"),
    UNAUTHORIZED(401, "未登录"),
    BAD_REQUEST(400, "请求错误"),
    USER_PASSWORD_NOT_MATCH(40001003, "用户名或密码错误"),
    USER_NAME_DUPLICATE(40001001, "用户名已存在"),
    USER_NOT_FOUND(40001002, "用户未找到"),
    USER_NOT_ENABLE(40001003, "用户已失效"),
    FORBIDDEN(403, "无权限操作"),

    TOKEN_IS_EXPIRED(40301001, "用户验证已失效，请重新验证"),
    USER_LOCKED(40301002, "用户被锁定"),
    NOT_FOUND(404, "未找到资源");


    private final Integer code;

    public final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
