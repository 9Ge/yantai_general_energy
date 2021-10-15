package com.enercomn.web.service;

import com.enercomn.web.bean.dto.RegisterDto;
import com.enercomn.web.bean.properties.GeneralProperties;
import com.enercomn.util.HttpsClient;
import com.enercomn.util.ObjectMapperUtil;
import com.enercomn.util.SpringContextHolder;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2021/10/9 10:55<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Log4j2
public class SingletonRegister {

    private static GeneralProperties generalProperties= SpringContextHolder.getBean(GeneralProperties.class);
    private static HttpsClient httpsClient= SpringContextHolder.getBean(HttpsClient.class);
    private static SingletonRegister singletonRegister = null;
    private RegisterDto registerDto;

    private SingletonRegister(RegisterDto registerDto) {
        this.registerDto = registerDto;
    }
    static{
        if(generalProperties == null){
            try {
                generalProperties = SpringContextHolder.getBean("generalProperties");
            } catch (Exception e) {
                log.error("SingletonRegister 初始化单例失败");
            }
        }
    }
    public static SingletonRegister instance() {
        if (singletonRegister == null) {
            synchronized (SingletonRegister.class) {
                if (singletonRegister == null) {
                    singletonRegister = new SingletonRegister(register());
                }
            }
        }
        return singletonRegister;
    }

    public RegisterDto getRegisterDto() {
        return registerDto;
    }


    private static RegisterDto register(){
        Map param  = new HashMap(2);
        param.put("enterpriseCode", generalProperties.getEnterpriseCode());
        param.put("region",generalProperties.getRegion());
        Map body = httpsClient.postClient(param,param,generalProperties.getRegisterUrl());
        return ObjectMapperUtil.mapToBean(body, RegisterDto.class);
    }
}
