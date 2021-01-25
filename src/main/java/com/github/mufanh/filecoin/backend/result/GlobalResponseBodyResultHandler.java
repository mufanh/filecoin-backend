package com.github.mufanh.filecoin.backend.result;

import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

/**
 * @author xinquan.huangxq
 */
public class GlobalResponseBodyResultHandler extends ResponseBodyResultHandler {

    public GlobalResponseBodyResultHandler(List<HttpMessageWriter<?>> writers,
                                           RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }

    public GlobalResponseBodyResultHandler(List<HttpMessageWriter<?>> writers,
                                           RequestedContentTypeResolver resolver,
                                           ReactiveAdapterRegistry registry) {
        super(writers, resolver, registry);
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        Object returnValue = result.getReturnValue();
        Object body;
        if (returnValue instanceof Mono) {
            body = ((Mono<?>) result.getReturnValue())
                    .map((Function<Object, Object>) GlobalResponseBodyResultHandler::wrapSuccessResult)
                    .defaultIfEmpty(Result.success());
        } else if (returnValue instanceof Flux) {
            body = ((Flux<?>) result.getReturnValue())
                    .collectList()
                    .map((Function<Object, Object>) GlobalResponseBodyResultHandler::wrapSuccessResult)
                    .defaultIfEmpty(Result.success());
        } else {
            body = wrapSuccessResult(returnValue);
        }
        return writeBody(body, result.getReturnTypeSource(), exchange);
    }

    private static Result<?> wrapSuccessResult(Object body) {
        if (body instanceof Result) {
            return (Result<?>) body;
        }
        return Result.success(body);
    }
}
