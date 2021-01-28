package com.github.mufanh.filecoin.backend.lotus.service;

import com.github.mufanh.filecoin.backend.lotus.domain.MyMinerInfo;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
public interface LotusService {

    Mono<MyMinerInfo> getMyMinerInfo(String minerId);
}
