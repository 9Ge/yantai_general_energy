package com.enercomn.web.controller;

import com.enercomn.web.bean.dto.UploadEnterpriseData;
import com.enercomn.web.service.GeneralEnergyService;
import com.enercomn.util.constant.ResultMsg;
import com.enercomn.util.constant.ResultStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2021/9/7 14:20<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@Api(value = "用能单位采集数据手动上传",tags = "用能单位采集数据手动上传")
public class UploadEnterpriseDataController {

    @Autowired
    private GeneralEnergyService generalEnergyService;

    @ApiOperation(value = "用能单位采集数据上传", notes = "用能单位采集数据上传", response = HashMap.class)
    @RequestMapping(value = "/energy", method = RequestMethod.POST)
    public ResultMsg importExcel(@RequestBody UploadEnterpriseData uploadEnterpriseData) throws Exception {
        try {
            Map map = generalEnergyService.uploadEncryptEnterpriseData(uploadEnterpriseData);
            Object responseCode = map.get("responseCode");
            if(responseCode == null || !String.valueOf(responseCode).equals(0)){
                return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(),"上传失败，失败原因："+map.get("responseMessage"),map);
            }
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(),map);
        } catch (Exception e) {
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(),"上传失败，失败原因：{}"+e.getMessage());
        }
    }


}
