package com.enercomn.web.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@Table(name = "tb_energy_data")
@ApiModel
public class TbEnergyData {

    @Id
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("上传数据项编码")
    private String dataCode;
    @ApiModelProperty("数据采集类型：1 管理信息系统；2 生产监控管理系统；3 分布式控制系统 4 现场仪表；5 手工填报")
    private String inputType;
    @ApiModelProperty("数据采集频率：0 实时、1 日、2 月、3 年")
    private String statType;
    @ApiModelProperty("定时选择器 0 每天-7点 1 每月1号-7点 2 每年-1月1号-7点")
    private String scheduledSelector;
    @ApiModelProperty("数据范围：1 全厂；2 生产工序；3 生产工序单元；4 重点耗能设备")
    private String scope;
    @ApiModelProperty("定时任务公式[不可编辑自动生成]")
    private String scheduledCorn;
    @ApiModelProperty("数据有效性：0有效数据；1无效数据")
    private String valid;

    @Transient
    @ApiModelProperty("redis关联关系")
    private List<TbEnergyDataCollectorRelation> tbEnergyDataCollectorRelationList;


}
