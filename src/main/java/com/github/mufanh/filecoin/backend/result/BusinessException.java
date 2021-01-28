package com.github.mufanh.filecoin.backend.result;

import lombok.Getter;

/**
 * @author xinquan.huangxq
 */
public final class BusinessException extends RuntimeException {

    @Getter
    private final String code;

    public BusinessException(String code ,String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrCode errCode) {
        super(errCode.getMessage());
        this.code = errCode.getCode();
    }

    public BusinessException(ErrCode errCode, String message) {
        super(message);
        this.code = errCode.getCode();
    }

    public BusinessException(ErrCode errCode, Throwable e) {
        super(errCode.getMessage(), e);
        this.code = errCode.getCode();
    }
}
