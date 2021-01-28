package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.lotus.domain.MyMinerInfo;
import com.github.mufanh.filecoin.backend.lotus.service.LotusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/lotus")
public class LotusController {

    @Autowired
    private LotusService lotusService;

    @RequestMapping(value = "/miner/{minerId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Mono<MyMinerInfo> lotusData(@PathVariable("minerId") String minerId) {
        return lotusService.getMyMinerInfo(minerId);
    }
}
