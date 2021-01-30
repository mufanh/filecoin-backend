package com.github.mufanh.filecoin.backend.filscan.domain;

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
@ApiModel("Filscan区块链概况")
public class StatChainInfo {

    @ApiModelProperty("最新区块高度")
    private String latestHeight;

    @ApiModelProperty("最近出块奖励")
    private String latestBlockReward;

    @ApiModelProperty("全网出块数量")
    private Long totalBlocks;

    @ApiModelProperty("全网出块奖励")
    private String totalRewards;

    @ApiModelProperty("存储速度（全网近24小时）")
    private String powerRatio;

    @ApiModelProperty("算力收益（全网近24小时）")
    private String filPerTera;

    @ApiModelProperty("全网有效算力（单位B）")
    private String totalQualityPower;

    @ApiModelProperty("活跃矿工数")
    private Long activeMiners;
}
