package com.github.mufanh.filecoin.backend.spider;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * filecoin交易所信息
 *
 * @author xinquan.huangxq
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@ApiModel("FileCoin价格信息")
public class FilecoinPrice implements Serializable {

    @ApiModelProperty("人民币/FIL")
    private Double cny;

    @ApiModelProperty("美元/FIL")
    private Double usd;

    @ApiModelProperty("价格变化率（24小时内）")
    private Double priceChangePercentage24h;
}
