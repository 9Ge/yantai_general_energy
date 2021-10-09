package com.enercomn.util.constant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("固定返回格式")
public class ResultMsg {
    /**
     * 错误码
     */
    @ApiModelProperty("错误码")
    private int resultCode;
    private String resultMessage;
    private Object resultObject;
    public ResultMsg() {
    }

    public ResultMsg(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ResultMsg(int resultCode, String resultMessage, Object resultObject) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultObject = resultObject;
    }


    public ResultMsg(Object resultObject) {
        this.resultCode = ResultStatusCode.OK.getResultCode();
        this.resultMessage = ResultStatusCode.OK.getResultMessage();
        this.resultObject = resultObject;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }


    @Override
    public String toString() {
        return "ResultMsg{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultObject=" + resultObject +
                '}';
    }


}
