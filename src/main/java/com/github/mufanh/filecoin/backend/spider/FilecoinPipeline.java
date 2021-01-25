package com.github.mufanh.filecoin.backend.spider;

import com.github.mufanh.filecoin.backend.data.FilecoinRepo;
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
        FilecoinRepo.getInstance().setSpiderData(spiderData);
    }
}
