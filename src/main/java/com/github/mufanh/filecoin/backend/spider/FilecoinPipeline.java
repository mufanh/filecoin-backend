package com.github.mufanh.filecoin.backend.spider;

import com.github.mufanh.filecoin.backend.data.FilecoinRepo;
import com.github.mufanh.filecoin.backend.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @author xinquan.huangxq
 */
@Slf4j
public class FilecoinPipeline implements Pipeline {

    @Override
    public void process(ResultItems items, Task task) {
        FilecoinSpiderData spiderData = new FilecoinSpiderData();

        for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
            if (!(entry.getValue() instanceof String)) {
                continue;
            }
            spiderData.setByLabel(entry.getKey(), (String) entry.getValue());
        }
        log.info("爬虫抓取网址：{}，获取到的信息为：{}", SpiderConstants.COINGECKO_4_FILECOIN_URL, JSONUtils.object2json(spiderData));

        FilecoinRepo.getInstance().setSpiderData(spiderData);
    }
}
