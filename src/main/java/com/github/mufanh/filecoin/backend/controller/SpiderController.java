package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.result.BusinessException;
import com.github.mufanh.filecoin.backend.result.ErrCode;
import com.github.mufanh.filecoin.backend.result.Result;
import com.github.mufanh.filecoin.backend.spider.CoingeckoInfo;
import com.github.mufanh.filecoin.backend.spider.SpiderRepo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @GetMapping(value = "/coingecko/info", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "交易所信息", response = Result.class, httpMethod = "GET")
    public Mono<CoingeckoInfo> coingecko() {
        if (SpiderRepo.getRepo().getCoingeckoInfo() == null) {
            throw new BusinessException(ErrCode.SPIDER_DATA_UNLOAD, "Filecoin交易所数据未加载完成，请等待几分钟后操作");
        }
        return Mono.just(SpiderRepo.getRepo().getCoingeckoInfo());
    }
}
