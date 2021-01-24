package com.github.mufanh.filecoin.backend.spider;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xinquan.huangxq
 */
@Data
@Slf4j
public class FilecoinSpiderData {
    // FIL Price : FIL价格
    // Market Cap : 总市值
    // Market Cap Dominance : 市场优势
    // Trading Volume : 成交量
    // Volume / Market Cap : 流通量/总市值
    // Market Cap Rank : 总市值排名
    // All-Time High : 历史最高
    // All-Time Low : 历史最低

    private String filPrice;

    private String marketCap;

    private String marketCapDominance;

    private String tradingVolume;

    private String volumeRate;

    private String marketCapRank;

    private String allTimeHigh;

    private String allTimeLow;

    public void setByLabel(String label, String value) {
        switch (label) {
            case "FIL Price":
                setFilPrice(value);
                break;
            case "Market Cap":
                setMarketCap(value);
                break;
            case "Market Cap Dominance":
                setMarketCapDominance(value);
                break;
            case "Trading Volume":
                setTradingVolume(value);
                break;
            case "Volume / Market Cap":
                setVolumeRate(value);
                break;
            case "Market Cap Rank":
                setMarketCapRank(value);
                break;
            case "All-Time High":
                setAllTimeHigh(value);
                break;
            case "All-Time Low":
                setAllTimeLow(value);
                break;
            default:
                log.warn("忽略爬到的数据{}：{}", label, value);
        }
    }
}
