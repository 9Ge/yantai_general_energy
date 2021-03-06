package com.enercomn.web.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@ApiModel
public class UploadEnterpriseData {

    @ApiModelProperty(hidden = true)
    private String deviceId;

    public UploadEnterpriseData(String deviceId, String enterpriseCode, List<UploadDeviceData> data) {
        this.deviceId = deviceId;
        this.enterpriseCode = enterpriseCode;
        this.data = data;
    }

    @ApiModelProperty(hidden = true)
    private String enterpriseCode;
    @ApiModelProperty(required = true)
    private List<UploadDeviceData> data;

    public UploadEnterpriseData() {
    }
}
