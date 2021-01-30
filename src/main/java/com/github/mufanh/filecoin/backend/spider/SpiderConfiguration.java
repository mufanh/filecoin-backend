package com.github.mufanh.filecoin.backend.spider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

/**
 * @author xinquan.huangxq
 */
@Configuration
@DependsOn("spiderProperties")
public class SpiderConfiguration {

    @Bean
    public SpiderPageProcessor filecoinPageProcessor(SpiderProperties properties, List<PageHandler> pageHandlers) {
        return new SpiderPageProcessor(properties, pageHandlers);
    }

    @Bean
    public SpiderPipeline filecoinPipeline() {
        return new SpiderPipeline();
    }
}
