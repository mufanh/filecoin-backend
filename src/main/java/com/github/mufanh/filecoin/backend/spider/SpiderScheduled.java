package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * 爬虫调度
 *
 * @author xinquan.huangxq
 */
@Component
@Slf4j
public class SpiderScheduled implements ApplicationRunner {

    @Autowired
    private Spider filecoinSpider;

    @Scheduled(cron = "${spider.cron}")
    public void filecoinSpiderScheduled() {
        filecoinSpider.start();
    }

    @Override
    public void run(ApplicationArguments args) {
        filecoinSpider.start();
    }
}
