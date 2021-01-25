package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.data.FilecoinRepo;
import com.github.mufanh.filecoin.backend.lotus.FilecoinLotusData;
import com.github.mufanh.filecoin.backend.result.BusinessException;
import com.github.mufanh.filecoin.backend.result.ErrCode;
import com.github.mufanh.filecoin.backend.spider.FilecoinSpiderData;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/filecoin")
public class FilecoinRepoController {

    @RequestMapping(value = "/spider", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Mono<FilecoinSpiderData> spiderData() {
        FilecoinSpiderData filecoinSpiderData = FilecoinRepo.getInstance().getSpiderData();
        if (filecoinSpiderData == null) {
            throw new BusinessException(ErrCode.SPIDER_DATA_UNLOAD, "Filecoin交易所数据未加载完成，请等待几分钟后操作");
        }
        return Mono.just(FilecoinRepo.getInstance().getSpiderData());
    }

    @RequestMapping(value = "/lotus", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Mono<FilecoinLotusData> lotusData() {
        FilecoinLotusData filecoinLotusData = FilecoinRepo.getInstance().getLotusData();
        if (filecoinLotusData == null) {
            throw new BusinessException(ErrCode.LOTUS_DATA_UNLOAD, "Lotus数据未加载完成，请等待几分钟后操作");
        }
        return Mono.just(FilecoinRepo.getInstance().getLotusData());
    }
}
