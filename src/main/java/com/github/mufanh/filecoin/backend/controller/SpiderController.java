package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.result.BusinessException;
import com.github.mufanh.filecoin.backend.result.ErrCode;
import com.github.mufanh.filecoin.backend.spider.ExchangeInfo;
import com.github.mufanh.filecoin.backend.spider.SpiderInfoRepo;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @RequestMapping(value = "/exchange-info", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Mono<ExchangeInfo> spiderData() {
        if (SpiderInfoRepo.getRepo().getExchangeInfo() == null) {
            throw new BusinessException(ErrCode.SPIDER_DATA_UNLOAD, "Filecoin交易所数据未加载完成，请等待几分钟后操作");
        }
        return Mono.just(SpiderInfoRepo.getRepo().getExchangeInfo());
    }
}
