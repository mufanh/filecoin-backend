package com.github.mufanh.filecoin.backend.result;

import lombok.Getter;

/**
 * @author xinquan.huangxq
 */
public enum ErrCode {
    // 流程不建议直接使用
    SUCCESS("0", "成功"),
    ERROR("1", "失败"),
    UNKNOWN_ERROR("2", "内部错误"),

    // 流程可使用错误码
    BAD_REQUEST("10001", "请求报文格式错误"),

    SPIDER_DATA_UNLOAD("10003", "Spider数据未爬取完成"),
    JSON_RPC_INVOKE_ERROR("10004", "JSON-RPC调用异常，获取信息失败"),
    LOTUS_DATA_GET_NONE("10005", "Lotus API执行获取信息为空，请检查Lotus同步状态"),
    ;

    @Getter
    private final String code;

    @Getter
    private final String message;

    ErrCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
