package com.github.mufanh.filecoin.backend.lotus;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xinquan.huangxq
 */
@Data
public class FilecoinLotusData implements Serializable {

    private Long height;

    /**
     * 	StageHeaders = 1
     * 	StagePersistHeaders = 2
     * 	StageMessages = 3
     * 	StageSyncComplete = 4
     * 	StageSyncErrored = 5
     * 	StageFetchingMessages = 6
     */
    private Integer stage;

    // Sum of raw byte power for a miner's sectors.
    private Long rawBytePower;

    // Sum of quality adjusted power for a miner's sectors.
    private Long qualityAdjPower;

    private Long totalRawBytePower;

    private Long totalQualityAdjPower;
}
