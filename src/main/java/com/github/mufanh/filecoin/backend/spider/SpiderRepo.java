package com.github.mufanh.filecoin.backend.spider;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xinquan.huangxq
 */
public class SpiderRepo {

    private static final SpiderRepo instance = new SpiderRepo();

    private AtomicReference<CoingeckoInfo> coingeckoInfo = new AtomicReference<>();

    private AtomicReference<FilscountOverview> filscountOverview = new AtomicReference<>();

    public CoingeckoInfo getCoingeckoInfo() {
        return coingeckoInfo.get();
    }

    public void setCoingeckoInfo(CoingeckoInfo info) {
        coingeckoInfo.set(info);
    }

    public FilscountOverview getFilscountOverview() {
        return filscountOverview.get();
    }

    public void setFilscountOverview(FilscountOverview info) {
        filscountOverview.set(info);
    }

    private SpiderRepo() {
    }

    public static SpiderRepo getRepo() {
        return instance;
    }
}
