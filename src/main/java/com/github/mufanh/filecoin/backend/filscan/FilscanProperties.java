package com.github.mufanh.filecoin.backend.filscan;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xinquan.huangxq
 */
@Component
@ConfigurationProperties(prefix = "filscan")
@Data
public class FilscanProperties {

    private String apiGateway;

    private String authorization;

    private int connectTimeout;

    private int readTimeout;

    private int writeTimeout;
}
