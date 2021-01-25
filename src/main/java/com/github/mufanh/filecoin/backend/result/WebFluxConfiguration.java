package com.github.mufanh.filecoin.backend.result;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author xinquan.huangxq
 */
@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {

    @Bean
    public GlobalResponseBodyResultHandler responseWrapper(
            ServerCodecConfigurer serverCodecConfigurer,RequestedContentTypeResolver requestedContentTypeResolver) {
        return new GlobalResponseBodyResultHandler(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }
}
