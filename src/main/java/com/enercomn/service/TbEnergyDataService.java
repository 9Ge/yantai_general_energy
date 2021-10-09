package com.enercomn.service;

import com.enercomn.bean.TbEnergyData;
import com.enercomn.bean.dto.UploadDeviceData;

import java.util.List;

/**
 * @Date: 2021/10/9 15:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public interface TbEnergyDataService {
    List<UploadDeviceData> queryEnergyData();
}
