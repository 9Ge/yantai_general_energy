package com.enercomn.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 2021/10/9 10:50<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@Configuration
@EnableConfigurationProperties(GeneralProperties.class)
@ConfigurationProperties(prefix = "energy.url")
public class GeneralProperties {

    private String enterpriseCode;
    private String region;
    private String registerUrl;
    private String uploadEnergyUrl;
}
