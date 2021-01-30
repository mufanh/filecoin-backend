package com.github.mufanh.filecoin.backend.spider;

import lombok.Data;

/**
 * @author xinquan.huangxq
 */
@Data
public class FilscountOverview {
    private Integer avg_block_time;
    private Integer avg_block_header_size;
    private Double avg_gas_price;
    private Double avg_message_size;
    private Double total_block_reward_in_one_day;
    private Double total_block_reward_in_one_week;
    private Double total_block_reward_in_one_month;
    private Double total_block_reward_in_one_year;
    private Double avg_blocks_tipset;
    private Integer avg_messages_tipset;
    private Integer active_miners;
    private Double flow_rate;
    private Integer flow_rate_str;
    private Integer one_day_deals;
    private Long total_fil;
    private Double one_day_fil;
    private Double current_fil;
    private Double last_turnover;
    private Long one_day_messages;
    private Double mining_income_one_day;
    private String mining_income_str_one_day;
    private String avg_messages_in_tipset_str;
    private String avg_blocks_in_tipset_str;
    private String avg_gas_premium;
    private String total_fil_str;
    private String one_day_fil_str;
    private String current_fil_str;
    private String last_turnover_str;
    private String one_day_messages_str;
    private Integer net_type;
    private Price price;
    private Market market;
    private String block_reward;
    private Long tipset_height;
    private Power power;
    private String pledge_collateral;
    private String current_base_fee;
    private String current_base_fee_str;
    private Long total_accounts;

    @Data
    public static class Price {
        private String unit_price;
        private String alteration;
    }

    @Data
    public static class Market {
        private String capitalization;
    }

    @Data
    public static class Power {
        private String total_power;
        private Long power_in_bytes;
        private Long raw_power_in_bytes;
    }
}
