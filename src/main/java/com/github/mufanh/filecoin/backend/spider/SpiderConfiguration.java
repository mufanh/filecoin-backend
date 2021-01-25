package com.github.mufanh.filecoin.backend.spider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import us.codecraft.webmagic.Spider;

import static com.github.mufanh.filecoin.backend.spider.SpiderProperties.CLIMB_URL;

/**
 * @author xinquan.huangxq
 */
@Configuration
@DependsOn("spiderProperties")
public class SpiderConfiguration {

    @Bean
    public FilecoinPageProcessor filecoinPageProcessor(SpiderProperties properties) {
        return new FilecoinPageProcessor(properties);
    }

    @Bean
    public FilecoinPipeline filecoinPipeline() {
        return new FilecoinPipeline();
    }

    @Bean
    public Spider filecoinSpider(SpiderProperties properties, FilecoinPageProcessor filecoinPageProcessor, FilecoinPipeline filecoinPipeline) {
        return Spider.create(filecoinPageProcessor)
                .addUrl(CLIMB_URL)
                .addPipeline(filecoinPipeline)
                .thread(properties.getThreadNum())
                .setExitWhenComplete(true);
    }
}
