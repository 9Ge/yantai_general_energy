package com.enercomn.time;

import com.enercomn.web.bean.TbEnergyData;
import com.enercomn.web.service.GeneralEnergyService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date: 2021/10/15 13:13<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component
@Log4j2
@SuppressWarnings("all")
public class EnergyDataTask {

    public final static String PREFIX="TASK";
    @Mapper
    public interface EnergyDataTaskCronMapper {
        @Select("select id, tb_energy_data.data_code dataCode,tb_energy_data.input_type inputType,tb_energy_data.stat_type statType," +
                "tb_energy_data.scope,tb_energy_data.valid ,tb_energy_data.scheduled_corn scheduledCorn ,tb_energy_data.scheduled_selector scheduledSelector  " +
                "from tb_energy_data")
        List<TbEnergyData> getCron();
    }

    @Resource
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;

    @Autowired
    EnergyDataTaskCronMapper energyDataTaskCronMapper;
    @Autowired
    private GeneralEnergyService generalEnergyService;

    private List<TbEnergyData> tbEnergyDataList;

    public List<TbEnergyData> getTbEnergyDataList() {
        return tbEnergyDataList;
    }

    public void setTbEnergyDataList(List<TbEnergyData> tbEnergyDataList) {
        this.tbEnergyDataList = tbEnergyDataList;
    }


    public void openTask() throws InterruptedException {
        while (!defaultSchedulingConfigurer.inited()) {
            Thread.sleep(100);
        }
        this.tbEnergyDataList = energyDataTaskCronMapper.getCron();
        tbEnergyDataList.forEach(tbEnergyData -> {
            defaultSchedulingConfigurer.addTriggerTask(PREFIX + tbEnergyData.getId(),
                    new TriggerTask(
                            () -> {
                                log.info(tbEnergyData.getDataCode() + ",定时公式：" + tbEnergyData.getScheduledCorn() + "，开始执行定时任务");
                                generalEnergyService.uploadData(tbEnergyData);
                            }
                            ,
                            new CronTrigger(tbEnergyData.getScheduledCorn())
                    ));
        });
    }

    /**
     * 添加任务
     * @param tbEnergyData
     * @throws InterruptedException
     */
    public void addTask(TbEnergyData tbEnergyData) throws InterruptedException {
        while (!defaultSchedulingConfigurer.inited()) {
            Thread.sleep(100);
        }
        defaultSchedulingConfigurer.addTriggerTask(PREFIX + tbEnergyData.getId(),
                new TriggerTask(
                        () -> {
                            log.info(tbEnergyData.getDataCode() + ",定时公式：" + tbEnergyData.getScheduledCorn() + "，开始执行定时任务");
                            generalEnergyService.uploadData(tbEnergyData);
                        }
                        ,
                        new CronTrigger(tbEnergyData.getScheduledCorn())
                ));
    }


    /**
     * 修改任务
     * @param taskId
     * @param tbEnergyData
     * @throws InterruptedException
     */
    public void updateTask(String taskId,TbEnergyData tbEnergyData) throws InterruptedException {
        while (!defaultSchedulingConfigurer.inited()) {
            Thread.sleep(100);
        }
        defaultSchedulingConfigurer.resetTriggerTask(taskId,new TriggerTask(
                () -> {
                    log.info(tbEnergyData.getDataCode() + ",定时公式：" + tbEnergyData.getScheduledCorn() + "，开始执行定时任务");
                    generalEnergyService.uploadData(tbEnergyData);
                }
                ,
                new CronTrigger(tbEnergyData.getScheduledCorn())
        ));
    }

    /**
     * 取消任务
     * @param taskId
     */
    public void cancelTask(String taskId) {
        defaultSchedulingConfigurer.cancelTriggerTask(taskId);
    }
}
