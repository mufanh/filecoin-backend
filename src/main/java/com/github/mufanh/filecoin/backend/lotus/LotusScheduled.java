package com.github.mufanh.filecoin.backend.lotus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xinquan.huangxq
 */
@Component
public class LotusScheduled {

    @Autowired
    private LotusProperties properties;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void filecoinLotusScheduled() {
    }
}
