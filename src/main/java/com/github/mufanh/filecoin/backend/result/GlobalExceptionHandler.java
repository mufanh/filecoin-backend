package com.github.mufanh.filecoin.backend.result;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebInputException;

/**
 * @author xinquan.huangxq
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result<?> serviceExceptionHandler(BusinessException ex) {
        return Result.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = ServerWebInputException.class)
    @ResponseBody
    public Result<?> serverWebInputExceptionHandler(ServerWebInputException ex) {
        return Result.error(ErrCode.BAD_REQUEST.getCode(), ErrCode.BAD_REQUEST.getMessage());
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        return Result.error(ErrCode.UNKNOWN_ERROR.getCode(), ErrCode.UNKNOWN_ERROR.getMessage());
    }
}
