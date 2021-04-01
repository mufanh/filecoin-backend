package com.github.mufanh.filecoin.backend.spider;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author xinquan.huangxq
 */
@JsonNaming
@Data
@ApiModel("Filfox区块链概要信息")
public class FilfoxOverview {

    private int height;

    private int timestamp;

    private String totalRawBytePower;

    private String totalQualityAdjPower;

    private String totalRawBytePowerDelta;

    private String totalQualityAdjPowerDelta;

    private int accounts;

    private int activeMiners;

    private String totalMaxSupply;

    private String totalSupply;

    private String circulatingSupply;

    private String burntSupply;

    private String totalPledgeCollateral;

    private String totalMultisigLockedBalance;

    private String totalMarketPledge;

    private String blockReward;

    private int dailyMessages;

    private String dailyCoinsMined;

    private double averageTipsetInterval;

    private double averageTipsetBlocks;

    private double averageTipsetWeightedBlocks;

    private String baseFee;

    private double averageRewardPerByte;

    private double estimatedInitialPledgeCollateral;

    private int sealCost;

    private double price;

    private double priceChangePercentage;
}
