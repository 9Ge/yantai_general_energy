package com.enercomn.web.service;

import com.enercomn.web.bean.dto.UploadDeviceData;

import java.util.List;

/**
 * @Date: 2021/10/9 15:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public interface TbEnergyDataService {
    List<UploadDeviceData> queryEnergyData();
}
