package com.github.mufanh.filecoin.backend.filscan;

import com.github.mufanh.filecoin.backend.filscan.domain.StatChainInfo;
import com.github.mufanh.jsonrpc4j.Call;
import com.github.mufanh.jsonrpc4j.annotation.JsonRpcMethod;
import com.github.mufanh.jsonrpc4j.annotation.JsonRpcService;

/**
 * @author xinquan.huangxq
 */
@JsonRpcService
public interface FilscanService {

    @JsonRpcMethod("filscan.StatChainInfo")
    Call<StatChainInfo> chainInfo();
}
