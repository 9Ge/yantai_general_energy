package com.enercomn.service.impl;

import com.enercomn.bean.TbEnergyData;
import com.enercomn.bean.dto.UploadDeviceData;
import com.enercomn.mapper.TbEnergyDataMapper;
import com.enercomn.service.TbEnergyDataService;
import com.enercomn.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2021/10/9 15:55<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
public class TbEnergyDataServiceImpl implements TbEnergyDataService {
    @Autowired
    private TbEnergyDataMapper tbEnergyDataMapper;

    @Override
    public List<UploadDeviceData> queryEnergyData() {
        List<TbEnergyData> tbEnergyData = tbEnergyDataMapper.selectAll();
        return  ObjectMapperUtil.convertToList(ObjectMapperUtil.writeValueAsString(tbEnergyData), UploadDeviceData.class);
    }
}
