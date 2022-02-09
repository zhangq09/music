package com.study.music.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer code;

    private String message;

    private Object trace;
}
