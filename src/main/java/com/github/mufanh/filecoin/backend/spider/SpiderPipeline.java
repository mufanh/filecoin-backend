package com.github.mufanh.filecoin.backend.spider;

import com.github.mufanh.filecoin.backend.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static com.github.mufanh.filecoin.backend.spider.FilecoinPriceHandler.SPIDER_FIELD_COINGECKO_INFO;
import static com.github.mufanh.filecoin.backend.spider.FilfoxPageHandler.SPIDER_FIELD_FILFOX_INFO;
import static com.github.mufanh.filecoin.backend.spider.FilscountPageHandler.SPIDER_FIELD_FILSCOUNT_INFO;

/**
 * @author xinquan.huangxq
 */
@Slf4j
public class SpiderPipeline implements Pipeline {

    @Override
    public void process(ResultItems items, Task task) {
        if (MapUtils.isEmpty(items.getAll())) {
            return;
        }

        FilecoinPrice coingeckoInfo = items.get(SPIDER_FIELD_COINGECKO_INFO);
        if (coingeckoInfo != null) {
            log.info("爬虫爬取到coingecko信息:{}", JSONUtils.object2json(coingeckoInfo));

            SpiderRepo.getRepo().setCoingeckoInfo(coingeckoInfo);
        }

        FilscountOverview filscountOverview = items.get(SPIDER_FIELD_FILSCOUNT_INFO);
        if (filscountOverview != null) {
            log.info("爬虫爬取到filscount信息:{}", filscountOverview);

            SpiderRepo.getRepo().setFilscountOverview(filscountOverview);
        }

        FilfoxOverview filfoxOverview = items.get(SPIDER_FIELD_FILFOX_INFO);
        if (filfoxOverview != null) {
            log.info("爬虫爬取到filfox信息:{}", filfoxOverview);

            SpiderRepo.getRepo().setFilfoxOverview(filfoxOverview);
        }
    }
}
