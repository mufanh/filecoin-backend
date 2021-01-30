package com.github.mufanh.filecoin.backend.utils;

import com.github.mufanh.filecoin.backend.result.BusinessException;
import com.github.mufanh.filecoin.backend.result.ErrCode;
import com.github.mufanh.jsonrpc4j.Call;
import com.github.mufanh.jsonrpc4j.Callback;
import com.github.mufanh.jsonrpc4j.Response;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @author xinquan.huangxq
 */
public final class MonoUtils {

    private MonoUtils() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static <T> Mono<T> convertCall2Mono(Call<T> call) {
        CompletableFuture<T> future = new CompletableFuture<>();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.getRawResponse() == null || !response.getRawResponse().isSuccessful()) {
                    future.completeExceptionally(new BusinessException(ErrCode.JSON_RPC_INVOKE_ERROR));
                    return;
                }
                future.complete(response.getResult());
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                future.completeExceptionally(new BusinessException(ErrCode.JSON_RPC_INVOKE_ERROR, throwable));
            }
        });

        return Mono.fromFuture(future);
    }
}
