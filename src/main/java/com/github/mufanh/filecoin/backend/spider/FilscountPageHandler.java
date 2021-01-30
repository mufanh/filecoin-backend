package com.github.mufanh.filecoin.backend.spider;

import com.github.mufanh.filecoin.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

/**
 * {
 * "code": 200,
 * "error": "ok",
 * "data": {
 * "statistic": {
 * "avg_block_time": 30,
 * "avg_block_header_size": 4787,
 * "avg_gas_price": 5212461.5,
 * "avg_message_size": 1053.0714,
 * "total_block_reward_in_one_day": 242783.20199857303,
 * "total_block_reward_in_one_week": 1577118.6806222752,
 * "total_block_reward_in_one_month": 6115771.471858615,
 * "total_block_reward_in_one_year": 2120.601355003317,
 * "avg_blocks_tipset": 4.491769,
 * "avg_messages_tipset": 416,
 * "active_miners": 1239,
 * "flow_rate": 0.03832537,
 * "flow_rate_str": 0,
 * "one_day_deals": 0,
 * "total_fil": 2000000000,
 * "one_day_fil": 241139.6058205449,
 * "current_fil": 76650733.18903731,
 * "last_turnover": 3231764.223916572,
 * "one_day_messages": 1190224,
 * "mining_income_one_day": 0.10626656043012052,
 * "mining_income_str_one_day": "0.1063",
 * "avg_messages_in_tipset_str": "416",
 * "avg_blocks_in_tipset_str": "4.5",
 * "avg_gas_premium": "5212461.5000",
 * "total_fil_str": "2,000,000,000",
 * "one_day_fil_str": "241139.6058",
 * "current_fil_str": "76650733.1890 FIL",
 * "last_turnover_str": "3231764.2239",
 * "one_day_messages_str": "1190224",
 * "net_type": 0,
 * "price": {
 * "unit_price": "22.5",
 * "alteration": "2.73"
 * },
 * "market": {
 * "capitalization": "$1.72 billion"
 * },
 * "block_reward": "19.8448 FIL",
 * "tipset_height": 454271,
 * "power": {
 * "total_power": "2.18 EiB",
 * "power_in_bytes": 2508835829263400960,
 * "raw_power_in_bytes": 2508404799448285184
 * },
 * "pledge_collateral": "34,081,982.2288",
 * "current_base_fee": "3778971109",
 * "current_base_fee_str": "3.7790 NanoFIL",
 * "total_accounts": 147755* 		}
 * }
 * }
 *
 * @author xinquan.huangxq
 */
@Component
public class FilscountPageHandler implements PageHandler {

    public static final String CLIMB_URL_4_FILSCOUNT = "https://filscoutv3api.ipfsunion.cn/network/overview";
    public static final String SPIDER_FIELD_FILSCOUNT_INFO = "FILSCOUNT_INFO";

    @Override
    public String url() {
        return CLIMB_URL_4_FILSCOUNT;
    }

    @Override
    public void handle(Page page) {
        if (page.getJson() == null) {
            return;
        }
        Selectable code = page.getJson().jsonPath("code");
        if (code == null || !StringUtils.equals("200", code.get())) {
            return;
        }
        Selectable statistic = page.getJson().jsonPath("data.statistic");
        if (statistic == null) {
            return;
        }
        FilscountOverview filscountOverview = JSONUtils.json2Object(statistic.get(), FilscountOverview.class);
        page.putField(SPIDER_FIELD_FILSCOUNT_INFO, filscountOverview);
    }
}
