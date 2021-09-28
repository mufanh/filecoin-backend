package com.github.mufanh.filecoin.backend.spider;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xinquan.huangxq
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@ApiModel("Filscount区块链概要信息")
public class FilscountOverview {
    @ApiModelProperty("平均出块时间（最近24小时内）")
    private Integer avgBlockTime;
    private Integer avgBlockHeaderSize;
    @ApiModelProperty("平均Gas单价（最近24小时内）")
    private Double avgGasPrice;
    private Double avgMessageSize;
    private Double totalBlockRewardInOneDay;
    private Double totalBlockRewardInOneWeek;
    private Double totalBlockRewardInOneMonth;
    private Double totalBlockRewardInOneYear;
    @ApiModelProperty("平均每个高度区块数（最近24小时内）")
    private Double avgBlocksTipset;
    @ApiModelProperty("平均每个高度消息数（最近24小时内）")
    private Integer avgMessagesTipset;
    @ApiModelProperty("活跃矿工数（近24小时和链有消息交互）")
    private Integer activeMiners;
    @ApiModelProperty("FIL流通率（当前释放总量占FIL百分比）")
    private Double flowRate;
    @ApiModelProperty("FIL流通率（当前释放总量占FIL百分比）")
    private Integer flowRateStr;
    private Integer oneDayDeals;
    @ApiModelProperty("FIL总量")
    private Long totalFil;
    @ApiModelProperty("24小时Fil增量")
    private Double oneDayFil;
    @ApiModelProperty("流通总量")
    private Double currentFil;
    private Double lastTurnover;
    @ApiModelProperty("24小时消息增量")
    private Long oneDayMessages;
    @ApiModelProperty("24小时平均挖矿收益")
    private Double miningIncomeOneDay;
    @ApiModelProperty("24小时平均挖矿收益")
    private String miningIncomeStrOneDay;
    private String avgMessagesInTipsetStr;
    private String avgBlocksInTipsetStr;
    private String avgGasPremium;
    private String totalFilStr;
    @ApiModelProperty("24小时Fil增量")
    private String oneDayFilStr;
    @ApiModelProperty("流通总量")
    private String currentFilStr;
    private String lastTurnoverStr;
    private String oneDayMessagesStr;
    private Integer netType;
    private Price price;
    private Market market;
    @ApiModelProperty("最近区块奖励")
    private String blockReward;
    @ApiModelProperty("最新区块高度")
    private Long tipsetHeight;
    private Power power;
    @ApiModelProperty("总质押")
    private String pledgeCollateral;
    @ApiModelProperty("当前基础费率")
    private String currentBaseFee;
    @ApiModelProperty("当前基础费率（单位换算后展示）")
    private String currentBaseFeeStr;
    @ApiModelProperty("总账户数")
    private Long totalAccounts;

    @ApiModelProperty("交易所信息")
    private FilecoinPrice coingeckoInfo;

    @ApiModelProperty("filfox区块链概要信息")
    private FilfoxOverview filfoxOverview;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @ApiModel("价格")
    @Data
    public static class Price {
        @ApiModelProperty("FIL单价")
        private String unitPrice;
        private String alteration;
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @ApiModel("流通市场")
    @Data
    public static class Market {
        @ApiModelProperty("流通市值")
        private String capitalization;
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @ApiModel("算力")
    @Data
    public static class Power {
        @ApiModelProperty("全网总算力")
        private String totalPower;
        private Long powerInBytes;
        private Long rawPowerInBytes;
    }
}
