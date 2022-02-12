package com.study.music.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.study.music.enums.ExceptionType;
import com.study.music.exception.BizException;
import com.study.music.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public ErrorResponse bizExceptionHandler(BizException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(e.getCode());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTrace(e.getStackTrace());
        return errorResponse;
    }

    //Exception返回500的处理
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.INNER_ERROR.getCode());
        errorResponse.setMessage(ExceptionType.INNER_ERROR.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ErrorResponse tokenExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.TOKEN_IS_EXPIRED.getCode());
        errorResponse.setMessage(ExceptionType.TOKEN_IS_EXPIRED.getMessage());
        return errorResponse;
    }

    //无权限请求
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.FORBIDDEN.getCode());
        errorResponse.setMessage(ExceptionType.FORBIDDEN.getMessage());
        return errorResponse;
    }

    //错误参数
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> bizExceptionHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponseList.add(errorResponse);
        });
        return errorResponseList;
    }


}
