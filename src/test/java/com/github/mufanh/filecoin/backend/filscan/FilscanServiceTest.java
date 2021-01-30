package com.github.mufanh.filecoin.backend.filscan;

import com.github.mufanh.filecoin.backend.filscan.domain.StatChainInfo;
import com.github.mufanh.jsonrpc4j.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author xinquan.huangxq
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilscanServiceTest {

    @Autowired
    private FilscanService filscanService;

    @Test
    public void chainInfo() throws IOException {
        Response<StatChainInfo> response = filscanService.chainInfo().execute();
        Assert.assertNotNull(response.getResult());
    }
}