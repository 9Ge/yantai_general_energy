package com.enercomn.web.bean.vo;

/**
 * @Date: 2021/10/14 11:50<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public class QueryCollectorParamVo {

    private String startTime;
    private String endTime;
    private String dataCode;

    public QueryCollectorParamVo() {
    }

    public QueryCollectorParamVo(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public QueryCollectorParamVo(String startTime, String endTime, String dataCode) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dataCode = dataCode;
    }
}
