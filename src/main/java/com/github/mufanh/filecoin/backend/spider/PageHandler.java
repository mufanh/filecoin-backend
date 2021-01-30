package com.github.mufanh.filecoin.backend.spider;

import us.codecraft.webmagic.Page;

/**
 * @author xinquan.huangxq
 */
public interface PageHandler {

    String url();

    void handle(Page page);
}
