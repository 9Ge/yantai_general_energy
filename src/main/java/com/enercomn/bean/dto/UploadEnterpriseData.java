package com.enercomn.bean.dto;

import lombok.Data;

import java.util.List;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
public class UploadEnterpriseData {

    private String deviceId;
    private String enterpriseCode;
    private List<UploadDeviceData> data;

}
