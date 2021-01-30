package com.github.mufanh.filecoin.backend.spider;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xinquan.huangxq
 */
@Component
@ConfigurationProperties(prefix = "spider")
@Data
public class SpiderProperties {

    // 爬取设置的浏览器Agent
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";


    private int threadNum;

    private int downloadTimeout;
}
