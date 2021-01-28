package com.github.mufanh.filecoin.backend.lotus.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author xinquan.huangxq
 */
@Data
public class MyMinerInfo {

    /**
     * 矿工地址
     */
    private String minerId;

    /**
     * Owner
     */
    private String owner;

    /**
     * 节点标识
     */
    private String peerId;

    /**
     * 原值算力，单位Byte
     */
    private Long rawBytePower;

    /**
     * 有效算力，单位Byte
     */
    private Long qualityAdjPower;

    /**
     * 全网总原值算力
     */
    private Long totalRawBytePower;

    /**
     * 全网总有效算力
     */
    private Long totalQualityAdjPower;

    /**
     * 扇区大小，单位Byte
     */
    private Long sectorSize;

    /**
     * 账户余额
     */
    private BigInteger balance;
}
