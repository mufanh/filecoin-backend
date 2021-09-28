package com.github.mufanh.filecoin.backend.spider;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xinquan.huangxq
 */
public class SpiderRepo {

    private static final SpiderRepo instance = new SpiderRepo();

    private AtomicReference<FilecoinPrice> coingeckoInfo = new AtomicReference<>();

    private AtomicReference<FilscountOverview> filscountOverview = new AtomicReference<>();

    private AtomicReference<FilfoxOverview> filfoxOverview = new AtomicReference<>();

    public FilecoinPrice getCoingeckoInfo() {
        return coingeckoInfo.get();
    }

    public void setCoingeckoInfo(FilecoinPrice info) {
        coingeckoInfo.set(info);
    }

    public FilscountOverview getFilscountOverview() {
        return filscountOverview.get();
    }

    public void setFilscountOverview(FilscountOverview info) {
        filscountOverview.set(info);
    }

    public FilfoxOverview getFilfoxOverview() {
        return filfoxOverview.get();
    }

    public void setFilfoxOverview(FilfoxOverview info) {
        filfoxOverview.set(info);
    }

    private SpiderRepo() {
    }

    public static SpiderRepo getRepo() {
        return instance;
    }
}
