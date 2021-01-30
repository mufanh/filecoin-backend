package com.github.mufanh.filecoin.backend.spider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import us.codecraft.webmagic.Spider;

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

    @Bean
    public Spider filecoinSpider(SpiderProperties properties,
                                 SpiderPageProcessor spiderPageProcessor,
                                 SpiderPipeline spiderPipeline,
                                 List<PageHandler> pageHandlers) {
        return Spider.create(spiderPageProcessor)
                .addPipeline(spiderPipeline)
                .addUrl(pageHandlers.stream()
                        .map(PageHandler::url)
                        .toArray(String[]::new))
                .thread(properties.getThreadNum())
                .setExitWhenComplete(true);
    }
}
