package com.enercomn.web.service;

import com.enercomn.web.bean.TbEnergyData;
import com.enercomn.web.bean.dto.UploadDeviceData;

import java.util.List;

/**
 * @Date: 2021/10/9 15:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public interface TbEnergyDataService {


    /**
     * 新增
     *
     * @param tbEnergyData
     */
    boolean save(TbEnergyData tbEnergyData) throws InterruptedException;

    /**
     * 修改
     *
     * @param tbEnergyData
     */
    boolean update(TbEnergyData tbEnergyData) throws InterruptedException;

    /**
     * 删除
     *
     * @param tbEnergyData
     */
    boolean delete(TbEnergyData tbEnergyData) throws InterruptedException;

    List<TbEnergyData> query(TbEnergyData tbEnergyData);

}
