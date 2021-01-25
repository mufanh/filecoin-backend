package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
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
public class SpiderScheduled {

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void filecoinSpiderScheduled() {
        startSpider();
    }

    void startSpider() {
        log.info("开始从网址[{}]爬取filecoin数据", SpiderConstants.COINGECKO_4_FILECOIN_URL);

        Spider.create(new FilecoinPageProcessor())
                .addUrl(SpiderConstants.COINGECKO_4_FILECOIN_URL)
                .addPipeline(new FilecoinPipeline())
                .thread(5)
                .setExitWhenComplete(true)
                .start();
    }
}
