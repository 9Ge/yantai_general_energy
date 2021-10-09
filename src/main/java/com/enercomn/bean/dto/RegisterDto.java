package com.enercomn.bean.dto;

import lombok.Data;

/**
 * @Date: 2021/10/9 11:06<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
public class RegisterDto {

    private RegisterDto(){};

    private String responseCode;
    private String responseMessage;
    private String deviceId;
    private String loadDicVersionURL;
    private String centerDataURL;
    private String centerInfoDownloadURL;
    private String downloadBaseDataURL;
    private String uploadTime;
    private String secretKey;
}
