package com.github.mufanh.filecoin.backend.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author xinquan.huangxq
 */
@Slf4j
public class FilecoinPageProcessor implements PageProcessor {

    private final Site coingecko = Site
            .me()
            .setDomain("www.coingecko.com")
            // 10s下载超时时间
            .setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");

    @Override
    public void process(Page page) {
        if (StringUtils.equals(SpiderConstants.COINGECKO_4_FILECOIN_URL, page.getUrl().get())) {
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
        return coingecko;
    }
}
