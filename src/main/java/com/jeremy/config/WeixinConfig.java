package com.jeremy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: laizc
 * @Date: 2020/4/12 15:36
 * @Description:
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "weixin")
public class WeixinConfig {
    private String mpAppId;

    private String mpSecretKey;

    private String  mchId;

    private String mchKey;

    private String keyPath;

    private String notifyUrl;

    private Map<String,String> templateId;

}
