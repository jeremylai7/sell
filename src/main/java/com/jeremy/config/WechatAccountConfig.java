package com.jeremy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: laizc
 * @Date: 2020/5/7 20:39
 * @Description:
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "weixin")
public class WechatAccountConfig {

    private String openAppId;

    private String openAppSecret;
}
