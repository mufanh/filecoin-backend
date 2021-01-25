package com.github.mufanh.filecoin.backend.controller;

import com.github.mufanh.filecoin.backend.data.FilecoinRepo;
import com.github.mufanh.filecoin.backend.lotus.FilecoinLotusData;
import com.github.mufanh.filecoin.backend.spider.FilecoinSpiderData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinquan.huangxq
 */
@RestController
@RequestMapping("/filecoin")
public class FilecoinRepoController {


    @RequestMapping(value = "/spider", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public FilecoinSpiderData spiderData() {
        return FilecoinRepo.getInstance().getSpiderData();
    }

    @RequestMapping(value = "/lotus", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public FilecoinLotusData lotusData() {
        return FilecoinRepo.getInstance().getLotusData();
    }
}
