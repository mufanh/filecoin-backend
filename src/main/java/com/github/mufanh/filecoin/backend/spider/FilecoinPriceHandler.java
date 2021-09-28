package com.github.mufanh.filecoin.backend.spider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author xinquan.huangxq
 */
@Component
public class FilecoinPriceHandler implements PageHandler {

    public static final String CLIMB_URL_4_COINGECKO = "http://10.77.2.3:8080/grab/data/currency/FIL";

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

        FilecoinPrice coingeckoInfo = new FilecoinPrice();
        // 人名币/FIL
        Selectable cny = json.jsonPath("price_cny");
        if (cny != null) {
            coingeckoInfo.setCny(Double.parseDouble(cny.get()));
        }
        // 美元/FIL
        Selectable usd = json.jsonPath("price");
        if (cny != null) {
            coingeckoInfo.setUsd(Double.parseDouble(usd.get()));
        }
        // 24小时变化率
        Selectable priceChangePercentage24h = json.jsonPath("change_percent");
        if (priceChangePercentage24h != null) {
            // 4舍5入
            Double percent = Double.parseDouble(priceChangePercentage24h.get());
            coingeckoInfo.setPriceChangePercentage24h(Double.valueOf(String.format("%.2f", percent)));
        }
        page.putField(SPIDER_FIELD_COINGECKO_INFO, coingeckoInfo);
    }
}
