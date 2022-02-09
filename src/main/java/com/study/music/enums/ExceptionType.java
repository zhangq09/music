package com.study.music.enums;

public enum ExceptionType {
    INNER_ERROR(500,"系统内部错误"),
    UNAUTHORIZED(401,"未登录"),
    BAD_REQUEST(400,"请求错误"),
    FORBIDDEN(403,"无权限操作"),
    NOT_FOUND(404,"未找到资源"),
    USER_NAME_DUPLICATE(40001,"用户名已存在"),
    USER_NOT_FOUND(40002,"用户未找到");
    private final Integer code;

    public final String message;

    ExceptionType(Integer code,String message){
        this.code=code;
        this.message=message;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
