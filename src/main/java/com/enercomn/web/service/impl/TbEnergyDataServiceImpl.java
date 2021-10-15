package com.enercomn.web.service.impl;

import com.enercomn.time.EnergyDataTask;
import com.enercomn.util.StringUtils;
import com.enercomn.web.bean.TbEnergyData;
import com.enercomn.web.mapper.TbEnergyDataMapper;
import com.enercomn.web.service.TbEnergyDataCollectorRelationService;
import com.enercomn.web.service.TbEnergyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2021/10/9 15:55<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class TbEnergyDataServiceImpl implements TbEnergyDataService {
    @Autowired
    private TbEnergyDataMapper tbEnergyDataMapper;
    @Autowired
    private TbEnergyDataCollectorRelationService tbEnergyDataCollectorRelationService;

    private final String EVERY_DAY_CORN = "0 0 7 * * ?";
    private final String EVERY_MONTH_CORN = "0 0 7 1 * ?";
    private final String EVERY_YEAR_CORN = "0 0 7 1 1 *";

    private String getCornBySelector(int selector) {
        switch (selector) {
            case 0:
                return EVERY_DAY_CORN;
            case 1:
                return EVERY_MONTH_CORN;
            case 2:
                return EVERY_YEAR_CORN;
            default:
                return null;
        }
    }

    /**
     * 新增
     *
     * @param tbEnergyData
     * @return
     */
    @Override
    public boolean save(TbEnergyData tbEnergyData) throws InterruptedException {
        tbEnergyData.setId(StringUtils.getUUID());
        this.setCorn(tbEnergyData);
        tbEnergyDataMapper.insert(tbEnergyData);
        if (tbEnergyData.getTbEnergyDataCollectorRelationList() != null) {
            tbEnergyData.getTbEnergyDataCollectorRelationList().forEach(o -> {
                o.setTbEnergyDataId(tbEnergyData.getId());
            });
            tbEnergyDataCollectorRelationService.saveBatch(tbEnergyData.getTbEnergyDataCollectorRelationList());
        }
        setScheduleTask(tbEnergyData, 0);
        return true;
    }


    public void setCorn(TbEnergyData tbEnergyData) {
        try {
            tbEnergyData.setScheduledCorn(getCornBySelector(Integer.valueOf(tbEnergyData.getScheduledSelector())));
        } catch (NumberFormatException e) {
            log.error("设置Corn失败，{}", tbEnergyData);
        }
    }

    /**
     * 修改
     *
     * @param tbEnergyData
     * @return
     */
    @Override
    public boolean update(TbEnergyData tbEnergyData) throws InterruptedException {
        Map<String, String> param = new HashMap<>();
        param.put("tbEnergyDataId", tbEnergyData.getId());
        tbEnergyDataCollectorRelationService.deleteByMap(param);

        this.setCorn(tbEnergyData);
        tbEnergyDataMapper.updateByPrimaryKey(tbEnergyData);
        if (tbEnergyData.getTbEnergyDataCollectorRelationList() != null) {
            tbEnergyData.getTbEnergyDataCollectorRelationList().forEach(o -> {
                o.setTbEnergyDataId(tbEnergyData.getId());
            });
            tbEnergyDataCollectorRelationService.saveBatch(tbEnergyData.getTbEnergyDataCollectorRelationList());
        }

        setScheduleTask(tbEnergyData, 1);
        return true;
    }

    /**
     * 删除
     *
     * @param tbEnergyData
     */
    @Override
    public boolean delete(TbEnergyData tbEnergyData) throws InterruptedException {
        Map<String, String> param = new HashMap<>();
        param.put("tbEnergyDataId", tbEnergyData.getId());
        tbEnergyDataCollectorRelationService.deleteByMap(param);

        tbEnergyDataMapper.deleteByPrimaryKey(tbEnergyData);
        setScheduleTask(tbEnergyData, 2);
        return true;
    }

    @Override
    public List<TbEnergyData> query(TbEnergyData tbEnergyData) {
        List<TbEnergyData> energyData = tbEnergyDataMapper.selectAll();

        energyData.forEach(e -> {
            Map<String, String> param = new HashMap<>();
            param.put("tbEnergyDataId", e.getId());
            e.setTbEnergyDataCollectorRelationList(tbEnergyDataCollectorRelationService.queryByMap(param));
        });
        return energyData;
    }

    @Autowired
    private EnergyDataTask energyDataTask;

    /**
     * @param tbEnergyData
     * @param flag         0新增 1修改 2删除
     */
    public void setScheduleTask(TbEnergyData tbEnergyData, int flag) throws InterruptedException {

        if (flag == 0) {
            energyDataTask.addTask(tbEnergyData);
        } else if (flag == 1) {
            energyDataTask.updateTask(EnergyDataTask.PREFIX + tbEnergyData.getId(), tbEnergyData);
        } else {
            energyDataTask.cancelTask(EnergyDataTask.PREFIX + tbEnergyData.getId());
        }


    }

}
