package com.jeremy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: laizc
 * @Date: 2020/5/7 21:59
 * @Description:
 */
@Getter
@Setter
@ConfigurationProperties("projectUrl")
@Component
public class ProjectUrlConfig {
    private String wechatMpAuthorize;

    private String wechatOpenAuthorize;

    private String sell;

}
