package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * 爬虫调度
 *
 * @author xinquan.huangxq
 */
@Component
@Slf4j
public class SpiderScheduled implements ApplicationRunner {

    @Autowired
    private SpiderPageProcessor spiderPageProcessor;

    @Autowired
    private SpiderPipeline spiderPipeline;

    @Autowired
    private List<PageHandler> pageHandlers;

    @Autowired
    private SpiderProperties spiderProperties;

    @Scheduled(cron = "${spider.cron}")
    public void filecoinSpiderScheduled() {
        runFilecoinSpider();
    }

    @Override
    public void run(ApplicationArguments args) {
        runFilecoinSpider();
    }

    public void runFilecoinSpider() {
        Spider spider = Spider.create(spiderPageProcessor)
                .addPipeline(spiderPipeline)
                .addUrl(pageHandlers.stream()
                        .map(PageHandler::url)
                        .toArray(String[]::new))
                .thread(spiderProperties.getThreadNum())
                .setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}
