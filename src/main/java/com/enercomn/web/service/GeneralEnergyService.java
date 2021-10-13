package com.enercomn.web.service;

import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.bean.dto.RegisterDto;
import com.enercomn.web.bean.dto.UploadDeviceData;
import com.enercomn.web.bean.dto.UploadEnterpriseData;
import com.enercomn.web.bean.properties.GeneralProperties;
import com.enercomn.util.HttpsClient;
import com.enercomn.util.ObjectMapperUtil;
import com.enercomn.util.Sm4Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
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
    @Autowired
    private TbEnergyDataCollectorRelationService tbEnergyDataCollectorRelationService;

    private static String key;
    private static String deviceId;

    /**
     * 获取设备数据
     *
     * @return
     */
    private List<UploadDeviceData> queryDeviceData() {
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

    /**
     * 定时采集数据
     * 保存采集数据
     * 推送采集数据
     * @return
     */
    public Map uploadData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TbEnergyDataCollectorValue> collectorValues = tbEnergyDataCollectorRelationService.redisMediumRelation();
        List<UploadDeviceData> uploadDeviceData = this.queryDeviceData();
        collectorValues.forEach(v->{
            uploadDeviceData.forEach(data->{
                if(data.getDataCode().equals(v.getDataCode())){
                    data.setDataValue(v.getRedisValue());
                    data.setStatDate(sdf.format(v.getPushTime()));
                    data.setUploadDate(sdf.format(v.getPushTime()));
                }
            });
        });
        if(!CollectionUtils.isEmpty(collectorValues)){
            UploadEnterpriseData uploadEnterpriseData = new UploadEnterpriseData();
            this.setRequestData(uploadEnterpriseData);

            List uploadDeviceData2 = this.encryptEcbRequestDataList(uploadDeviceData);
            uploadEnterpriseData.setData(uploadDeviceData2);
            return uploadEnterpriseData(uploadEnterpriseData);
        }
        return null;
    }

    public Map uploadEnterpriseData(UploadEnterpriseData uploadEnterpriseData) {
        this.setRequestData(uploadEnterpriseData);
        return httpsClient.postClient(ObjectMapperUtil.objectToMap(uploadEnterpriseData), generalProperties.getUploadEnergyUrl());
    }


    public void setRequestData(UploadEnterpriseData uploadEnterpriseData) {
        RegisterDto registerDto = SingletonRegister.instance().getRegisterDto();
        key = registerDto.getSecretKey();
        deviceId = registerDto.getDeviceId();

        uploadEnterpriseData.setDeviceId(deviceId);
        uploadEnterpriseData.setEnterpriseCode(generalProperties.getEnterpriseCode());
    }


}
