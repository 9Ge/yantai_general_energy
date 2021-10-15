package com.enercomn.web.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * tb_push_energy_data_collector
 * @author 
 */
@Data
@ApiModel
public class TbEnergyDataCollectorRelation implements Serializable {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    /**
     * redis键
     */
    @ApiModelProperty("redis键")
    private String redisKey;
    /**
     * 介质编码
     */
    @ApiModelProperty("介质编码[冗余字段]")
    private String dataCode;

    @ApiModelProperty("介质Id")
    private String tbEnergyDataId;

    private static final long serialVersionUID = 1L;
}