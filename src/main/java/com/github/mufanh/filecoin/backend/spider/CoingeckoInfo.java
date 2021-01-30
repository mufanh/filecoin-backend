package com.github.mufanh.filecoin.backend.spider;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * filecoin交易所信息
 *
 * @author xinquan.huangxq
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@ApiModel("Filecoin交易所信息")
public class CoingeckoInfo implements Serializable {

    /**
     * 人名币/FIL
     */
    @ApiModelProperty("人民币/FIL")
    private Double cny;

    /**
     * 美元/FIL
     */
    @ApiModelProperty("美元/FIL")
    private Double usd;

    /**
     * 排名
     */
    @ApiModelProperty("排名")
    private Integer rank;

    /**
     * 市场总价值（人名币）
     */
    @ApiModelProperty("市场总价值（人民币）")
    private Double marketCapCny;

    /**
     * 市场总价值（美元）
     */
    @ApiModelProperty("市场总价值（美元）")
    private Double marketCapUsd;
}
