package com.enercomn.service;

import com.enercomn.bean.TbEnergyData;
import com.enercomn.bean.dto.RegisterDto;
import com.enercomn.bean.dto.UploadDeviceData;
import com.enercomn.bean.dto.UploadEnterpriseData;
import com.enercomn.handler.anno.RequestLog;
import com.enercomn.properties.GeneralProperties;
import com.enercomn.util.HttpsClient;
import com.enercomn.util.ObjectMapperUtil;
import com.enercomn.util.Sm4Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 2021/10/9 11:20<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Log4j2
@Service
public class GeneralEnergyService {

    @Autowired
    private HttpsClient httpsClient;
    @Autowired
    private GeneralProperties generalProperties;
    @Autowired
    private TbEnergyDataService tbEnergyDataService;

    private static String key;
    private static String deviceId;

    /**
     * 获取设备数据
     *
     * @return
     */
    private  List<UploadDeviceData> queryDeviceData() {
        List<UploadDeviceData> uploadDeviceData = tbEnergyDataService.queryEnergyData();
        return uploadDeviceData;
    }

    /**
     * 加密请求数组
     *
     * @param uploadDeviceDatas
     * @return
     */
    public List<UploadDeviceData> encryptEcbRequestDataList(List<UploadDeviceData> uploadDeviceDatas) {
        List<UploadDeviceData> newRequestDataList = new ArrayList<>();
        uploadDeviceDatas.forEach(data -> {
            try {
                newRequestDataList.add(this.encryptEcbRequestData(data));
            } catch (Exception e) {
                log.error("加密数据失败，{}", e);
            }
        });
        return newRequestDataList;
    }

    /**
     * 加密 水电燃气请求数据
     *
     * @param deviceData
     * @return
     * @throws Exception
     */
    private UploadDeviceData encryptEcbRequestData(UploadDeviceData deviceData) throws Exception {
        Map<String, String> map = (Map<String, String>) ObjectMapperUtil.objectToMap(deviceData);
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            entry.setValue(Sm4Util.encryptEcb(key, entry.getValue()));
        }
        return ObjectMapperUtil.mapToBean(map, UploadDeviceData.class);
    }

    public Map uploadData() {
        RegisterDto registerDto = SingletonRegister.instance().getRegisterDto();
        key = registerDto.getSecretKey();
        deviceId = registerDto.getDeviceId();

        UploadEnterpriseData uploadEnterpriseData = new UploadEnterpriseData();
        uploadEnterpriseData.setDeviceId(deviceId);
        uploadEnterpriseData.setEnterpriseCode(generalProperties.getEnterpriseCode());
        uploadEnterpriseData.setData(this.encryptEcbRequestDataList(this.queryDeviceData()));
        return uploadEnterpriseData(uploadEnterpriseData);
    }


    public Map uploadEnterpriseData( UploadEnterpriseData uploadEnterpriseData){
        return httpsClient.postClient(ObjectMapperUtil.objectToMap(uploadEnterpriseData), generalProperties.getUploadEnergyUrl());
    }


}
