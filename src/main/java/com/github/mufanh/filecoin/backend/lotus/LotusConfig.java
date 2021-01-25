package com.github.mufanh.filecoin.backend.lotus;

import com.github.mufanh.filecoin4j.rpc.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author xinquan.huangxq
 */
@Configuration
@DependsOn("lotusProperties")
public class LotusConfig {

    @Bean
    public LotusAPIFactory lotusAPIFactory(LotusProperties properties) {
        return LotusAPIFactory.of(properties.getApiGateway(), properties.getAuthorization());
    }

    @Bean
    public LotusChainAPI lotusChainAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusChainAPI();
    }

    @Bean
    public LotusGasAPI lotusGasAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusGasAPI();
    }

    @Bean
    public LotusMinerAPI lotusMinerAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusMinerAPI();
    }

    @Bean
    public LotusMpoolAPI lotusMpoolAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusMPoolAPI();
    }

    @Bean
    public LotusSyncAPI lotusSyncAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusSyncAPI();
    }

    @Bean
    public LotusWalletAPI lotusWalletAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusWalletAPI();
    }

    @Bean
    public LotusStateAPI lotusStateAPI(LotusAPIFactory lotusAPIFactory) {
        return lotusAPIFactory.createLotusStateAPI();
    }
}
