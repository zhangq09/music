package com.study.music.exception;

import com.study.music.enums.ExceptionType;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private final Integer code;

    private final String message;

    public BizException(ExceptionType exceptionType){
        this.code=exceptionType.getCode();
        this.message=exceptionType.getMessage();
    };
}
