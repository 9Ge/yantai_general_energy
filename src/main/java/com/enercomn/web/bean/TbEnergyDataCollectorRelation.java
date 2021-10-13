package com.enercomn.web.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * tb_push_energy_data_collector
 * @author 
 */
@Data
public class TbEnergyDataCollectorRelation implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * redis键
     */
    private String redisKey;
    /**
     * 介质编码
     */
    private String dataCode;

    private static final long serialVersionUID = 1L;
}