package com.github.mufanh.filecoin.backend.spider;

import com.github.mufanh.filecoin.backend.utils.JSONUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

/**
 *{
 * 	"height": 632623,
 * 	"timestamp": 1617285090,
 * 	"totalRawBytePower": "4366509356523454464",
 * 	"totalQualityAdjPower": "4367548626787631104",
 * 	"totalRawBytePowerDelta": "27195561119580160",
 * 	"totalQualityAdjPowerDelta": "27186133875785728",
 * 	"accounts": 482485,
 * 	"activeMiners": 1766,
 * 	"totalMaxSupply": "2000000000000000000000000000",
 * 	"totalSupply": "645038152779187902447934168",
 * 	"circulatingSupply": "109025215451432166916968320",
 * 	"burntSupply": "20543311204274597401714437",
 * 	"totalPledgeCollateral": "55519356331492854865009721",
 * 	"totalMultisigLockedBalance": "465167702986748762010744927",
 * 	"totalMarketPledge": "5311805982845980170649",
 * 	"blockReward": "26446761337440743925",
 * 	"dailyMessages": 1534258,
 * 	"dailyCoinsMined": "371122049088209892096657",
 * 	"averageTipsetInterval": 30.220356768100732,
 * 	"averageTipsetBlocks": 4.850647079398391,
 * 	"averageTipsetWeightedBlocks": 4.9104582021685905,
 * 	"baseFee": "987844956",
 * 	"averageRewardPerByte": 29.73423474525764,
 * 	"estimatedInitialPledgeCollateral": 9139989.624626666,
 * 	"sealCost": 383771452997378240,
 * 	"price": 229.21,
 * 	"priceChangePercentage": 0.3789145
 * }
 * @author xinquan.huangxq
 */
@Component
public class FilfoxPageHandler implements PageHandler {
    public static final String CLIMB_URL_4_FILFOX = "https://filfox.info/api/v1/overview";
    public static final String SPIDER_FIELD_FILFOX_INFO = "FILFOX_INFO";

    @Override
    public String url() {
        return CLIMB_URL_4_FILFOX;
    }

    @Override
    public void handle(Page page) {
        if (page.getJson() == null) {
            return;
        }
        FilfoxOverview filfoxOverview = JSONUtils.json2Object(page.getJson().get(), FilfoxOverview.class);
        page.putField(SPIDER_FIELD_FILFOX_INFO, filfoxOverview);
    }
}
