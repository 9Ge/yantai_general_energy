package com.enercomn;


import com.alibaba.fastjson.JSONObject;
import com.enercomn.bean.dto.RegisterDto;
import com.enercomn.properties.GeneralProperties;
import com.enercomn.service.GeneralEnergyService;
import com.enercomn.util.HttpsClient;
import com.enercomn.util.HttpsClientRequestFactory;
import com.enercomn.util.ObjectMapperUtil;
import com.enercomn.service.SingletonRegister;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestDataTest {

    @Autowired
    GeneralProperties generalProperties;
    @Autowired
    GeneralEnergyService generalEnergyService;

    @Autowired
    private HttpsClient httpsClient;

    @Test
    public void test1() {
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));

        Map json  = new HashMap();
        json.put("enterpriseCode", generalProperties.getEnterpriseCode());
        json.put("region",generalProperties.getRegion());

        long time = System.currentTimeMillis();
        HttpEntity<Map> httpEntity = new HttpEntity<Map>(json, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("https://60.208.20.233:9244/enterprise/register2", httpEntity, Map.class);
        time = System.currentTimeMillis()-time;
        log.info( "运行"+time+"毫秒");
        log.info("地址响应信息："+response.getBody());

        Map body = response.getBody();
        RegisterDto registerDto = ObjectMapperUtil.mapToBean(body, RegisterDto.class);
        log.info("{}",registerDto);

    }@Test
    public void test6() throws Exception {
        generalEnergyService.uploadData();

    }
    @Test
    public void test4() {
        Map json  = new HashMap();
        json.put("enterpriseCode", generalProperties.getEnterpriseCode());
        json.put("region",generalProperties.getRegion());
        Map body = httpsClient.postClient(null,generalProperties.getRegisterUrl());
        RegisterDto registerDto = ObjectMapperUtil.mapToBean(body, RegisterDto.class);
        log.info("{}",registerDto);
    }
    @Test
    public void test5() {
        RegisterDto registerDto = SingletonRegister.instance().getRegisterDto();
        log.info("{}",registerDto);
    }

    @Test
    public void test2(){
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        JSONObject json  = new JSONObject();
        json.put("enterpriseCode","91370600710936591P");
        json.put("region","370672");

        long time = System.currentTimeMillis();
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(json, headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity("https://60.208.20.233:9244/enterprise/register", httpEntity, JSONObject.class);
        time = System.currentTimeMillis()-time;
        log.info( "运行"+time+"毫秒");
        log.info("地址响应信息："+response.getBody());
    }


    @Test
    public void test3(){
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        Map json  = new HashMap();
        json.put("enterpriseCode","91370600710936591P");
        json.put("region","370672222");

        long time = System.currentTimeMillis();
        HttpEntity<Map> httpEntity = new HttpEntity<Map>(json, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("https://60.208.20.233:9244/enterprise/register", httpEntity, Map.class);
        time = System.currentTimeMillis()-time;
        log.info( "运行"+time+"毫秒");
        log.info("地址响应信息："+response.getBody());
    }
}
