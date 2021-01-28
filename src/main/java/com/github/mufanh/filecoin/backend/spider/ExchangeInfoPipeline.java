package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @author xinquan.huangxq
 */
@Slf4j
public class ExchangeInfoPipeline implements Pipeline {

    @Override
    public void process(ResultItems items, Task task) {
        ExchangeInfo exchangeInfo = new ExchangeInfo();

        for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
            if (!(entry.getValue() instanceof String)) {
                continue;
            }
            exchangeInfo.setByLabel(entry.getKey(), (String) entry.getValue());
        }
        SpiderInfoRepo.getRepo().setExchangeInfo(exchangeInfo);
    }
}
