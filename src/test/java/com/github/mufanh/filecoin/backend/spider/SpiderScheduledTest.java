package com.github.mufanh.filecoin.backend.spider;

import org.junit.jupiter.api.Test;

/**
 * @author xinquan.huangxq
 */
class SpiderScheduledTest {

    @Test
    public void startSpider() throws InterruptedException {
        SpiderScheduled spiderScheduled = new SpiderScheduled();
        spiderScheduled.startSpider();

        synchronized (SpiderScheduledTest.class) {
            SpiderScheduledTest.class.wait();
        }
    }
}