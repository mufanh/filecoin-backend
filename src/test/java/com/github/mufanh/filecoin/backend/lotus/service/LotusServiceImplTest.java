package com.github.mufanh.filecoin.backend.lotus.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xinquan.huangxq
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LotusServiceImplTest {

    @Autowired
    private LotusService lotusService;

    @Test
    public void getMyMinerInfo() {
        String minerId = "f01248";
        lotusService.getMyMinerInfo(minerId)
                .subscribe((minerInfo) -> {
                    Assert.assertNotNull(minerInfo);
                    Assert.assertEquals(minerInfo.getMinerId(), minerId);
                });
    }
}