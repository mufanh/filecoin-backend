package com.github.mufanh.filecoin.backend.spider;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xinquan.huangxq
 */
public class SpiderInfoRepo {

    private static final SpiderInfoRepo instance = new SpiderInfoRepo();

    private AtomicReference<ExchangeInfo> exchangeInfo = new AtomicReference<>();

    public ExchangeInfo getExchangeInfo() {
        return exchangeInfo.get();
    }

    public void setExchangeInfo(ExchangeInfo info) {
        exchangeInfo.set(info);
    }

    private SpiderInfoRepo() {
    }

    public static SpiderInfoRepo getRepo() {
        return instance;
    }
}
