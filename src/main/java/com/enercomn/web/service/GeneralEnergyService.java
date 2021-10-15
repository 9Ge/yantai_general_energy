package com.enercomn.web.service;

import com.enercomn.util.DateTimeUtils;
import com.enercomn.util.HttpsClient;
import com.enercomn.util.ObjectMapperUtil;
import com.enercomn.util.Sm4Util;
import com.enercomn.web.bean.TbEnergyData;
import com.enercomn.web.bean.dto.RegisterDto;
import com.enercomn.web.bean.dto.UploadDeviceData;
import com.enercomn.web.bean.dto.UploadEnterpriseData;
import com.enercomn.web.bean.properties.GeneralProperties;
import com.enercomn.web.bean.vo.QueryCollectorParamVo;
import com.enercomn.web.mapper.TbEnergyDataCollectorValueMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private TbEnergyDataCollectorValueMapper tbEnergyDataCollectorValueMapper;

    private static String key;
    private static String deviceId;

    /**
     * 加密请求数组
     *
     * @param uploadDeviceDatas
     * @return
     */
    private List<UploadDeviceData> encryptEcbRequestDataList(List<UploadDeviceData> uploadDeviceDatas) {
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
     * 从采集数据中获取请求数据的值
     * 设置上传数据的采集时间和推送时间
     *
     * @param uploadDeviceData
     * @param collectorStartTime
     * @param collectorEndTime
     */
    private void setUploadProperties(UploadDeviceData uploadDeviceData, String collectorStartTime, String collectorEndTime) {
        UploadDeviceData collectorData = tbEnergyDataCollectorValueMapper.queryValueByCode(new QueryCollectorParamVo(collectorStartTime, collectorEndTime, uploadDeviceData.getDataCode()));
        if (uploadDeviceData == null) {
            return;
        } else if (collectorData == null) {
            uploadDeviceData.setDataValue("0");
        } else {
            uploadDeviceData.setDataValue(collectorData.getDataValue());
        }
        uploadDeviceData.setStatDate(collectorStartTime);
        uploadDeviceData.setUploadDate(collectorEndTime);
    }

    /**
     * 设置需要上传数据的信息
     * 加密上传的信息
     * 发送请求
     *
     * @param uploadEnterpriseData
     * @param uploadDeviceDataList
     * @return
     */
    private Map setUploadEnterpriseDataAndSend(UploadEnterpriseData uploadEnterpriseData, List<UploadDeviceData> uploadDeviceDataList) {
        uploadEnterpriseData.setData(uploadDeviceDataList);
        return uploadEncryptEnterpriseData(uploadEnterpriseData);
    }

    /**
     * 推送数据
     *
     * @param tbEnergyData 需要推送的介质
     * @return
     */
    public Map uploadData(TbEnergyData tbEnergyData) {
        Date date = new Date();
        String collectorStartTime = getCollectorTime(tbEnergyData, date);
        String collectorEndTime = new SimpleDateFormat(DateTimeUtils.YYYY_MM_DD_HH_MM_SS_POINT).format(date);

        UploadDeviceData uploadDeviceData = getUploadDeviceByEnergyData(tbEnergyData);
        setUploadProperties(uploadDeviceData, collectorStartTime, collectorEndTime);

        if (uploadDeviceData != null) {
            List<UploadDeviceData> uploadDeviceDataList = new ArrayList();
            uploadDeviceDataList.add(uploadDeviceData);
            return setUploadEnterpriseDataAndSend(new UploadEnterpriseData(), uploadDeviceDataList);
        } else {
            return null;
        }
    }

    private String getCollectorTime(TbEnergyData tbEnergyData, Date date) {
        String scheduledSelector = tbEnergyData.getScheduledSelector();
        switch (scheduledSelector) {
            case "0":
                return DateTimeUtils.before24hours(date, DateTimeUtils.YYYY_MM_DD_HH_MM_SS_POINT);
            case "1":
                return DateTimeUtils.beforeMonth1day(date, DateTimeUtils.YYYY_MM_DD_HH_MM_SS_POINT);
            case "2":
                return DateTimeUtils.year1Day1Month(date, DateTimeUtils.YYYY_MM_DD_HH_MM_SS_POINT);
        }
        return null;
    }

    /**
     * 数据库实体转换成上传数据实体
     *
     * @param tbEnergyData
     * @return
     */
    private UploadDeviceData getUploadDeviceByEnergyData(TbEnergyData tbEnergyData) {
        String dataStr = ObjectMapperUtil.writeValueAsString(tbEnergyData);
        return ObjectMapperUtil.convertTo(dataStr, UploadDeviceData.class);
    }


    /**
     * 上传加密数据
     *
     * @param uploadEnterpriseData
     * @return
     */
    public Map uploadEncryptEnterpriseData(UploadEnterpriseData uploadEnterpriseData) {

        UploadEnterpriseData encryptEnterpriseData = this.setRequestData(new UploadEnterpriseData(), uploadEnterpriseData.getData());

        uploadEnterpriseData.setDeviceId(encryptEnterpriseData.getDeviceId());
        uploadEnterpriseData.setEnterpriseCode(encryptEnterpriseData.getEnterpriseCode());
        return httpsClient.postClient(ObjectMapperUtil.objectToMap(uploadEnterpriseData), ObjectMapperUtil.objectToMap(encryptEnterpriseData), generalProperties.getUploadEnergyUrl());
    }

    /**
     * 设置请求体
     * 拼接请求体
     *
     * @param uploadEnterpriseData
     * @return
     */
    private UploadEnterpriseData setRequestData(UploadEnterpriseData uploadEnterpriseData, List deviceDataList) {
        RegisterDto registerDto = SingletonRegister.instance().getRegisterDto();
        key = registerDto.getSecretKey();
        deviceId = registerDto.getDeviceId();

        uploadEnterpriseData.setDeviceId(deviceId);
        uploadEnterpriseData.setEnterpriseCode(generalProperties.getEnterpriseCode());

        List<UploadDeviceData> encryptEcbDeviceData = this.encryptEcbRequestDataList(deviceDataList);
        uploadEnterpriseData.setData(encryptEcbDeviceData);
        return uploadEnterpriseData;
    }


}
