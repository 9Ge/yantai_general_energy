package com.enercomn.web.controller;

import com.enercomn.util.constant.ResultMsg;
import com.enercomn.util.constant.ResultStatusCode;
import com.enercomn.web.bean.TbEnergyData;
import com.enercomn.web.service.TbEnergyDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2021/10/13 14:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/collector_relation")
@Api(value = "介质采集器设置", tags = "介质采集器设置")
public class TbEnergyDataController {

    private final TbEnergyDataService tbEnergyDataService;

    public TbEnergyDataController(TbEnergyDataService tbEnergyDataService) {
        this.tbEnergyDataService = tbEnergyDataService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增")
    public ResultMsg addData(@RequestBody TbEnergyData param) {
        try {
            return new ResultMsg(tbEnergyDataService.save(param));
        } catch (RuntimeException | InterruptedException e) {
            log.info("{}:异常" + e.getMessage(), param);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除")
    public ResultMsg delData(@RequestBody TbEnergyData param) {
        try {
            return new ResultMsg(tbEnergyDataService.delete(param));
        } catch (RuntimeException | InterruptedException e) {
            log.info("{}:异常" + e.getMessage(), param);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新")
    public ResultMsg updateData(@RequestBody TbEnergyData param) {
        try {
            return new ResultMsg(tbEnergyDataService.update(param));
        } catch (Exception e) {
            log.info("{}:异常" + e.getMessage(), param);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询")
    public ResultMsg findDataList(@RequestBody TbEnergyData tbEnergyDataCollectorRelation) {
        try {
            return new ResultMsg(tbEnergyDataService.query(tbEnergyDataCollectorRelation));
        } catch (RuntimeException e) {
            log.info("{}:异常" + e.getMessage(), tbEnergyDataCollectorRelation);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }


}
