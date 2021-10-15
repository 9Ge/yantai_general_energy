package com.enercomn.web.controller;

import com.enercomn.web.bean.TbEnergyRequestLog;
import com.enercomn.web.service.TbEnergyRequestLogService;
import com.enercomn.util.constant.PageObject;
import com.enercomn.util.constant.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2021/10/12 15:33<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/upload_log")
@Api(value = "用能单位采集数据推送记录",tags = "用能单位采集数据推送记录")
public class TbEnergyRequestLogController {

    @Autowired
    private TbEnergyRequestLogService tbEnergyRequestLogService;

    @PostMapping("list")
    @ApiOperation(value = "查询")
    public ResultMsg list(@RequestBody PageObject<TbEnergyRequestLog> pageObject){
        return new ResultMsg(tbEnergyRequestLogService.queryLog(pageObject));
    }



}
