package com.github.mufanh.filecoin.backend.lotus;

import com.github.mufanh.filecoin.backend.utils.JSONUtils;
import com.github.mufanh.filecoin4j.domain.SyncState;
import com.github.mufanh.filecoin4j.rpc.LotusSyncAPI;
import com.github.mufanh.jsonrpc4j.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author xinquan.huangxq
 */
@Component
@Slf4j
public class LotusScheduled {

    @Autowired
    private LotusSyncAPI lotusSyncAPI;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void filecoinLotusScheduled() throws IOException {
        Response<SyncState> response =  lotusSyncAPI.state().execute();
        log.info(JSONUtils.object2json(response.getResult()));
    }
}
