package com.github.mufanh.filecoin.backend.lotus.service;

import com.github.mufanh.filecoin.backend.lotus.domain.MyMinerInfo;
import com.github.mufanh.filecoin.backend.result.BusinessException;
import com.github.mufanh.filecoin.backend.result.ErrCode;
import com.github.mufanh.filecoin4j.domain.MinerPower;
import com.github.mufanh.filecoin4j.domain.builtin.MinerInfo;
import com.github.mufanh.filecoin4j.domain.types.Actor;
import com.github.mufanh.filecoin4j.rpc.LotusStateAPI;
import com.github.mufanh.jsonrpc4j.Call;
import com.github.mufanh.jsonrpc4j.Callback;
import com.github.mufanh.jsonrpc4j.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.concurrent.CompletableFuture;

/**
 * @author xinquan.huangxq
 */
@Component
public class LotusServiceImpl implements LotusService {

    @Autowired
    private LotusStateAPI lotusStateAPI;

    @Override
    public Mono<MyMinerInfo> getMyMinerInfo(String minerId) {
        Mono<MinerInfo> minerInfoMono = convertCall2Mono(lotusStateAPI.minerInfo(minerId, null));
        Mono<MinerPower> minerPowerMono = convertCall2Mono(lotusStateAPI.minerPower(minerId, null));
        Mono<Actor> actorMono = convertCall2Mono(lotusStateAPI.getActor(minerId, null));
        return Mono.zip(minerInfoMono, minerPowerMono, actorMono)
                .flatMap(data -> {
                    MinerInfo minerInfo = data.getT1();
                    MinerPower minerPower = data.getT2();
                    Actor actor = data.getT3();

                    MyMinerInfo result = new MyMinerInfo();

                    result.setMinerId(minerId);
                    result.setOwner(minerInfo.getOwner());
                    result.setPeerId(minerInfo.getPeerId());
                    if (minerPower.getMinerPower() != null) {
                        result.setQualityAdjPower(minerPower.getMinerPower().getQualityAdjPower());
                        result.setRawBytePower(minerPower.getMinerPower().getRawBytePower());
                    }
                    if (minerPower.getTotalPower() != null) {
                        result.setTotalQualityAdjPower(minerPower.getTotalPower().getQualityAdjPower());
                        result.setTotalRawBytePower(minerPower.getTotalPower().getRawBytePower());
                    }
                    result.setSectorSize(minerInfo.getSectorSize());
                    result.setBalance(actor.getBalance());

                    return Mono.just(result);
                });
    }

    private <T> Mono<T> convertCall2Mono(Call<T> call) {
        CompletableFuture<T> future = new CompletableFuture<>();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.getRawResponse() == null || !response.getRawResponse().isSuccessful()) {
                    future.completeExceptionally(new BusinessException(ErrCode.LOTUS_INVOKE_ERROR));
                    return;
                }
                if (response.getResult() == null) {
                    future.completeExceptionally(new BusinessException(ErrCode.LOTUS_DATA_GET_NONE));
                    return;
                }
                future.complete(response.getResult());
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                future.completeExceptionally(new BusinessException(ErrCode.LOTUS_INVOKE_ERROR, throwable));
            }
        });

        return Mono.fromFuture(future);
    }
}
