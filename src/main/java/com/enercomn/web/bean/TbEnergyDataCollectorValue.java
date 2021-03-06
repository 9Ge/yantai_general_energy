package com.enercomn.web.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_push_energy_data_collector_log
 * @author 
 */
@Data
@ApiModel
public class TbEnergyDataCollectorValue implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * redis键
     */
    @ApiModelProperty("redis键")
    private String redisKey;


    /**
     * redis值
     */
    @ApiModelProperty("redis值")
    private String redisValue;

    /**
     * 介质编码
     */
    @ApiModelProperty("介质编码")
    private String dataCode;

    /**
     * 采集时间
     */
    @ApiModelProperty("采集时间")
    private Date collectorTime;

    private static final long serialVersionUID = 1L;
}
