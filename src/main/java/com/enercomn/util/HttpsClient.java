package com.enercomn.util;

import com.enercomn.exception.RequestException;
import com.enercomn.handler.anno.RequestLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2021/10/9 10:42<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Log4j2
@Service
public class HttpsClient {

    @RequestLog
    public Map postClient(Map param, String url) throws RequestException {
        try {
            ResponseEntity<Map> response = null;
            RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
            long time = System.currentTimeMillis();
            HttpEntity<Map> httpEntity = new HttpEntity<Map>(param, headers);
            log.info("请求对象：{}", ObjectMapperUtil.writeValueAsString(param));
            response = restTemplate.postForEntity(url, httpEntity, Map.class);
            time = System.currentTimeMillis() - time;
            log.info("运行" + time + "毫秒");
            log.info("地址响应信息：" + ObjectMapperUtil.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw  new RequestException(e);
        }
    }
}
