package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.filscan.FilscanService;
import com.github.mufanh.filecoin.backend.filscan.domain.StatChainInfo;
import com.github.mufanh.filecoin.backend.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.github.mufanh.filecoin.backend.utils.MonoUtils.convertCall2Mono;

/**
 * @author xinquan.huangxq
 */

@RestController
@RequestMapping("/filscan")
@Api("获取Filscan网站抓取的信息")
public class FilscanController {

    @Autowired
    private FilscanService filscanService;

    @GetMapping(value = "/chain/info", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "区块链概要信息", response = Result.class, httpMethod = "GET")
    public Mono<StatChainInfo> chainInfo() {
        return convertCall2Mono(filscanService.chainInfo());
    }
}
