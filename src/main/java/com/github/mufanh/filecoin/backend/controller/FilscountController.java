package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.filscan.domain.StatChainInfo;
import com.github.mufanh.filecoin.backend.result.BusinessException;
import com.github.mufanh.filecoin.backend.result.ErrCode;
import com.github.mufanh.filecoin.backend.result.Result;
import com.github.mufanh.filecoin.backend.spider.FilscountOverview;
import com.github.mufanh.filecoin.backend.spider.SpiderRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/filscount")
@Api("获取Filscount网站抓取的信息")
public class FilscountController {

    @GetMapping(value = "/overview", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "区块链概要信息", response = Result.class, httpMethod = "GET")
    public Mono<FilscountOverview> overview() {
        if (SpiderRepo.getRepo().getCoingeckoInfo() == null) {
            throw new BusinessException(ErrCode.SPIDER_DATA_UNLOAD, "Filscount数据未加载完成，请等待几分钟后操作");
        }
        return Mono.just(SpiderRepo.getRepo().getFilscountOverview());
    }
}
