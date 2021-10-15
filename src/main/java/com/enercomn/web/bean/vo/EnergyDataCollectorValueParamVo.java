package com.enercomn.web.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * tb_push_energy_data_collector_log
 *
 * @author
 */
@Data
@ApiModel
public class EnergyDataCollectorValueParamVo implements Serializable {
    @ApiModelProperty("redis键")
    private String redisKey;

    @ApiModelProperty("redis值")
    private String redisValue;

    @ApiModelProperty("介质编码")
    private String dataCode;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;


    private static final long serialVersionUID = 1L;
}
