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
    public ExchangeInfoPageProcessor filecoinPageProcessor(SpiderProperties properties) {
        return new ExchangeInfoPageProcessor(properties);
    }

    @Bean
    public ExchangeInfoPipeline filecoinPipeline() {
        return new ExchangeInfoPipeline();
    }

    @Bean
    public Spider filecoinSpider(SpiderProperties properties, ExchangeInfoPageProcessor exchangeInfoPageProcessor, ExchangeInfoPipeline exchangeInfoPipeline) {
        return Spider.create(exchangeInfoPageProcessor)
                .addUrl(CLIMB_URL)
                .addPipeline(exchangeInfoPipeline)
                .thread(properties.getThreadNum())
                .setExitWhenComplete(true);
    }
}
