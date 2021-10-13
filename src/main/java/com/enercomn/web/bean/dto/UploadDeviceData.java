package com.enercomn.web.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@ApiModel
public class UploadDeviceData {
    @ApiModelProperty(value = "上传数据项编码",required = true)
    private String dataCode;
    @ApiModelProperty(value = "数据项的值",required = true)
    private String dataValue;
    @ApiModelProperty(value = "要求数据采集类型：1 管理信息系统；2 生产监控管理系统；3 分布式控制系统；4 现场仪表；5 手工填报",required = true)
    private String inputType;
    @ApiModelProperty(value = "  数据采集频率：0 实时、1 日、2 月、3 年",required = true)
    private String statType;
    @ApiModelProperty(value = "数据统计时间 yyyy-MM-dd HH:mm:ss",required = true)
    private String statDate;
    @ApiModelProperty(value = "数据上传时间 yyyy-MM-dd HH:mm:ss",required = true)
    private String uploadDate;
    @ApiModelProperty(value = " 数据范围：1 全厂；2 生产工序；3 生产工序单元；4 重点耗能设备",required = true)
    private String scope;
    @ApiModelProperty(value = " 数据有效性：0有效数据；1无效数据",required = true)
    private String valid;


}
