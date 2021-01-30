package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

import static com.github.mufanh.filecoin.backend.spider.SpiderProperties.*;

/**
 * @author xinquan.huangxq
 */
@Slf4j
public class SpiderPageProcessor implements PageProcessor {

    private final Site site;

    private final List<PageHandler> pageHandlers;

    public SpiderPageProcessor(SpiderProperties spiderProperties, List<PageHandler> pageHandlers) {
        this.site = Site.me()
                .setTimeOut(spiderProperties.getDownloadTimeout())
                .setUserAgent(USER_AGENT);
        this.pageHandlers = pageHandlers;
    }

    @Override
    public void process(Page page) {
        pageHandlers.forEach(handler -> {
            if (!StringUtils.equals(page.getUrl().get(), handler.url())) {
                return;
            }
            handler.handle(page);
        });
    }

    @Override
    public Site getSite() {
        return site;
    }
}
