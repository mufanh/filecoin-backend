package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import static com.github.mufanh.filecoin.backend.spider.SpiderProperties.CLIMB_URL;
import static com.github.mufanh.filecoin.backend.spider.SpiderProperties.USER_AGENT;

/**
 * @author xinquan.huangxq
 */
@Slf4j
public class FilecoinPageProcessor implements PageProcessor {

    private final Site site;

    public FilecoinPageProcessor(SpiderProperties spiderProperties) {
        this.site = Site.me()
                .setTimeOut(spiderProperties.getDownloadTimeout())
                .setUserAgent(USER_AGENT);
    }

    @Override
    public void process(Page page) {
        if (StringUtils.equals(CLIMB_URL, page.getUrl().get())) {
            List<Selectable> list = page.getHtml()
                    .xpath("//div[@itemType='https://schema.org/Table']/table/tbody/tr")
                    .nodes();
            for (Selectable s : list) {
                String label = StringUtils.trim(s.xpath("//strong/text() | //th/text()").get());
                String value = StringUtils.trim(s.xpath("//span/text() | //td/text()").get());
                if (StringUtils.isBlank(label) || StringUtils.isBlank(value)) {
                    continue;
                }
                page.putField(label, value);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
