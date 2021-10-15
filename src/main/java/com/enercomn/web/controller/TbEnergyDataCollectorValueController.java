package com.enercomn.web.controller;

import com.enercomn.util.constant.PageObject;
import com.enercomn.util.constant.ResultMsg;
import com.enercomn.util.constant.ResultStatusCode;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.bean.vo.EnergyDataCollectorValueParamVo;
import com.enercomn.web.service.TbEnergyDataCollectorValueService;
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
@RequestMapping("/collector_value")
@Api(value = "采集数据信息",tags = "采集数据信息")
public class TbEnergyDataCollectorValueController {

    private final TbEnergyDataCollectorValueService tbEnergyDataCollectorValueService;

    public TbEnergyDataCollectorValueController(TbEnergyDataCollectorValueService tbEnergyDataCollectorValueService) {
        this.tbEnergyDataCollectorValueService = tbEnergyDataCollectorValueService;
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询")
    public ResultMsg findDataList(@RequestBody PageObject<EnergyDataCollectorValueParamVo> pageObject) {
        try {
            return new ResultMsg(tbEnergyDataCollectorValueService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }


}
