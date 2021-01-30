package com.github.mufanh.filecoin.backend.lotus.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author xinquan.huangxq
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@ApiModel("矿工信息")
public class MyMinerInfo {

    @ApiModelProperty("矿工地址")
    private String minerId;

    @ApiModelProperty("Owner")
    private String owner;

    @ApiModelProperty("节点标识")
    private String peerId;

    @ApiModelProperty("原值算力（单位B）")
    private Long rawBytePower;

    @ApiModelProperty("有效算力（单位B）")
    private Long qualityAdjPower;

    @ApiModelProperty("全网总原值算力（单位B）")
    private Long totalRawBytePower;

    @ApiModelProperty("全网总有效算力（单位B）")
    private Long totalQualityAdjPower;

    @ApiModelProperty("扇区大小")
    private Long sectorSize;

    @ApiModelProperty("余额")
    private BigInteger balance;
}
