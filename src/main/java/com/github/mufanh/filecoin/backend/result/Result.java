package com.github.mufanh.filecoin.backend.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;

import java.io.Serializable;

/**
 * 请求结果包装类
 *
 * @author xinquan.huangxq
 */
public class Result<T> implements Serializable {

    private static final String SUCCESS_CODE = ErrCode.SUCCESS.getCode();
    private static final String ERROR_CODE = ErrCode.ERROR.getCode();
    private static final String SUCCESS_MSG = ErrCode.SUCCESS.getMessage();
    private static final String ERROR_MSG = ErrCode.ERROR.getCode();

    @SuppressWarnings("rawtypes")
    private static final Result EMPTY_SUCCESS = new Result<>(SUCCESS_CODE, SUCCESS_MSG, null);

    @SuppressWarnings("rawtypes")
    private static final Result EMPTY_ERROR = new Result<>(ERROR_CODE, ERROR_MSG, null);


    @Getter
    private final String code;

    @Getter
    private final String message;

    @Getter
    private final T data;

    /**
     * success会被序列化出来
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    private Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) EMPTY_SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public static <T> Result<T> error() {
        return (Result<T>) EMPTY_ERROR;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> Result<T> error(Result<?> result) {
        return new Result<T>(result.getCode(), result.getMessage(), null);
    }

    public static <T> Result<T> error(String code, String message) {
        Preconditions.checkArgument(!StringUtils.equals(code, SUCCESS_CODE),
                "code必须为错误状态码，不能为SUCCESS");

        return new Result<T>(code, message, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR_CODE, message, null);
    }
}
