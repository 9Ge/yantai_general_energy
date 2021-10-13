package com.enercomn.web.bean;

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
public class TbEnergyRequestLog {
    @Id
    private String id;
    private Date requestTime;
    private String resultFlag;
    private String requestInfo;
    private String responseInfo;
    private String failedMessage;
    private Long requestLongTime;
    private String requestUrl;

}
