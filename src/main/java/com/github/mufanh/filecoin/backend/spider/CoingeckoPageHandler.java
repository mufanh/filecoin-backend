package com.github.mufanh.filecoin.backend.spider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author xinquan.huangxq
 */
@Component
public class CoingeckoPageHandler implements PageHandler {

    public static final String CLIMB_URL_4_COINGECKO = "https://api.coingecko.com/api/v3/coins/filecoin?developer_data=false&community_data=false&tickers=false";

    public static final String SPIDER_FIELD_COINGECKO_INFO = "EXCHANGE_INFO";

    @Override
    public String url() {
        return CLIMB_URL_4_COINGECKO;
    }

    @Override
    public void handle(Page page) {
        Json json = page.getJson();
        if (json == null) {
            return;
        }

        CoingeckoInfo coingeckoInfo = new CoingeckoInfo();
        // 人名币/FIL
        Selectable cny = json.jsonPath("market_data.current_price.cny");
        if (cny != null) {
            coingeckoInfo.setCny(Double.parseDouble(cny.get()));
        }
        // 美元/FIL
        Selectable usd = json.jsonPath("market_data.current_price.usd");
        if (cny != null) {
            coingeckoInfo.setUsd(Double.parseDouble(usd.get()));
        }
        // 市场排名
        Selectable rank = json.jsonPath("market_data.market_cap_rank");
        if (rank != null) {
            coingeckoInfo.setRank(Integer.parseInt(rank.get()));
        }
        // 人民币总价值
        Selectable marketCapCny = json.jsonPath("market_data.market_cap.cny");
        if (cny != null) {
            coingeckoInfo.setMarketCapCny(Double.parseDouble(marketCapCny.get()));
        }
        // 美元总价值
        Selectable marketCapUsd = json.jsonPath("market_data.market_cap.usd");
        if (cny != null) {
            coingeckoInfo.setMarketCapUsd(Double.parseDouble(marketCapUsd.get()));
        }
        page.putField(SPIDER_FIELD_COINGECKO_INFO, coingeckoInfo);
    }
}
