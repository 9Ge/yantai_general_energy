package com.enercomn.web.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@Table(name = "tb_energy_request_log")
@ApiModel
public class TbEnergyRequestLog {

    @Id
    private String id;
    @ApiModelProperty("请求时间")
    private Date requestTime;
    @ApiModelProperty("请求内容")
    private String requestInfo;
    @ApiModelProperty("请求结果")
    private String responseInfo;
    @ApiModelProperty("失败信息")
    private String failedMessage;
    @ApiModelProperty("请求时长（毫秒）")
    private Long requestLongTime;
    @ApiModelProperty("请求地址")
    private String requestUrl;
    @ApiModelProperty("结果标识")
    private String resultFlag;
    @ApiModelProperty("解密数据")
    private String requestInfoDecr;

}
