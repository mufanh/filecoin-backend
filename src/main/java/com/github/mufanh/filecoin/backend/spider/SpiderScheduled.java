package com.github.mufanh.filecoin.backend.spider;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * 爬虫调度
 *
 * @author xinquan.huangxq
 */
@Component
public class SpiderScheduled {

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void filecoinSpiderScheduled() {
        startSpider();
    }

    void startSpider() {
        Spider.create(new FilecoinPageProcessor())
                .addUrl(SpiderConstants.COINGECKO_4_FILECOIN_URL)
                .addPipeline(new FilecoinPipeline())
                .thread(5)
                .setExitWhenComplete(true)
                .start();
    }
}
