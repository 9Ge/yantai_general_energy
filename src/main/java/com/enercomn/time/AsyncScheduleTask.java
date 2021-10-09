package com.enercomn.time;

import com.enercomn.service.GeneralEnergyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    @Async
//    @Scheduled(cron = "0 0 5 * * ?")  //每天5点
//    @Scheduled(cron = "*/10 * * * * ?  ")  //每天5点
    public void everyDay() throws InterruptedException {
        log.info("开启定时任务，上传采集数据");
        generalEnergyService.uploadData();
    }



}
