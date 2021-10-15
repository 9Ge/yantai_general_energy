package com.enercomn.time;

import com.enercomn.util.DateTimeUtils;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.service.GeneralEnergyService;
import com.enercomn.web.service.TbEnergyDataCollectorRelationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2021/10/09 16:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component
@EnableScheduling
@EnableAsync
@Log4j2
@SuppressWarnings({"all"})
public class AsyncScheduleTask {

    @Autowired
    private GeneralEnergyService generalEnergyService;
    @Autowired
    private TbEnergyDataCollectorRelationService tbEnergyDataCollectorRelationService;
//    @Scheduled(cron = "*/5 * * * * ?")  //每天5点
    @Async
    @Scheduled(cron = "0 0 */1 * * ?")//每小时执行一次
    public void everyDay() throws InterruptedException {
        log.info("开始采集数据");
        List<TbEnergyDataCollectorValue> collectorValues = tbEnergyDataCollectorRelationService.redisMediumRelation();
    }

}
