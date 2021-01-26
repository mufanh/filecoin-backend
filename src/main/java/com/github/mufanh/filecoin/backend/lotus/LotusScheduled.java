package com.github.mufanh.filecoin.backend.lotus;

import com.github.mufanh.filecoin.backend.data.FilecoinRepo;
import com.github.mufanh.filecoin4j.domain.ActiveSyncs;
import com.github.mufanh.filecoin4j.domain.MinerPower;
import com.github.mufanh.filecoin4j.domain.SyncState;
import com.github.mufanh.filecoin4j.domain.builtin.MinerInfo;
import com.github.mufanh.filecoin4j.domain.types.TipSet;
import com.github.mufanh.filecoin4j.domain.types.TipSetKey;
import com.github.mufanh.filecoin4j.rpc.LotusStateAPI;
import com.github.mufanh.filecoin4j.rpc.LotusSyncAPI;
import com.github.mufanh.jsonrpc4j.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;


/**
 * @author xinquan.huangxq
 */
@Component
@Slf4j
public class LotusScheduled implements ApplicationRunner {

    @Autowired
    private LotusSyncAPI lotusSyncAPI;

    @Autowired
    private LotusStateAPI lotusStateAPI;

    @Scheduled(cron = "${lotus.cron}")
    public void filecoinLotusScheduled() {
        readSyncState();
    }

    @Override
    public void run(ApplicationArguments args) {
        readSyncState();
    }

    @SneakyThrows
    private void readSyncState() {
        Response<SyncState> syncStateResponse =  lotusSyncAPI.state().execute();

        SyncState syncState = syncStateResponse.getResult();
        if (syncState == null || CollectionUtils.isEmpty(syncState.getActiveSyncs())) {
            return;
        }

        // 取一个worker数据即可
        ActiveSyncs activeSyncs = syncState.getActiveSyncs().get(0);

        FilecoinLotusData filecoinLotusData = new FilecoinLotusData();
        filecoinLotusData.setHeight(activeSyncs.getHeight());
        filecoinLotusData.setStage(activeSyncs.getStage());

        // 同步到的TipSet
        TipSet tipSet = activeSyncs.getBase();
        TipSetKey tipSetKey = TipSetKey.of(tipSet.getCids());

        Response<MinerInfo> minerInfoResponse = lotusStateAPI.minerInfo("f01248", tipSetKey).execute();
        Response<MinerPower> minerPowerResponse = lotusStateAPI.minerPower("f01248", tipSetKey).execute();

        MinerInfo minerInfo = minerInfoResponse.getResult();
        MinerPower minerPower = minerPowerResponse.getResult();
        if (minerInfo == null || minerPower == null) {
            return;
        }

        filecoinLotusData.setRawBytePower(minerPower.getMinerPower().getRawBytePower());
        filecoinLotusData.setQualityAdjPower(minerPower.getMinerPower().getQualityAdjPower());
        filecoinLotusData.setTotalQualityAdjPower(minerPower.getTotalPower().getQualityAdjPower());
        filecoinLotusData.setTotalRawBytePower(minerPower.getTotalPower().getRawBytePower());

        FilecoinRepo.getInstance().setLotusData(filecoinLotusData);
    }
}
