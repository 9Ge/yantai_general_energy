package com.enercomn.bean.dto;

import lombok.Data;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
public class UploadDeviceData {

    private String dataCode;
    private String dataValue;
    private String inputType;
    private String statType;
    private String statDate;
    private String uploadDate;
    private String scope;
    private String valid;


}
