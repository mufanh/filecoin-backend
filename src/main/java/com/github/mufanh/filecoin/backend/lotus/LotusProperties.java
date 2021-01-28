package com.github.mufanh.filecoin.backend.lotus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xinquan.huangxq
 */
@Component
@ConfigurationProperties(prefix = "lotus")
@Data
public class LotusProperties {

    private String apiGateway;

    private String authorization;

    private int connectTimeout;

    private int readTimeout;

    private int writeTimeout;
}
