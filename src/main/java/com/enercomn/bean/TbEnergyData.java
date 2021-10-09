package com.enercomn.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@Table(name = "tb_energy_data")
public class TbEnergyData {

    @Id
    @JsonIgnore
    private String id;
    private String dataCode;
    private String dataValue;
    private String inputType;
    private String statType;
    private String statDate;
    private String uploadDate;
    private String scope;
    private String valid;

}
