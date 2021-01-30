package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.lotus.domain.MyMinerInfo;
import com.github.mufanh.filecoin.backend.lotus.service.LotusService;
import com.github.mufanh.filecoin.backend.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/lotus")
public class LotusController {

    @Autowired
    private LotusService lotusService;

    @GetMapping(value = "/miner/{minerId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "矿工信息", response = Result.class, httpMethod = "GET")
    public Mono<MyMinerInfo> lotusData(@PathVariable("minerId") @ApiParam("矿工地址") String minerId) {
        return lotusService.getMyMinerInfo(minerId);
    }
}
