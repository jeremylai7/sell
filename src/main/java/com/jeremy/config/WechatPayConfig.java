package com.jeremy.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**微信支付配置
 * @Auther: laizc
 * @Date: 2020/4/18 21:12
 * @Description:
 */
@Component
public class WechatPayConfig {
    @Autowired
    private WeixinConfig weixinConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(weixinConfig.getMpAppid());
        wxPayH5Config.setAppSecret(weixinConfig.getMpSecretKey());
        wxPayH5Config.setMchId(weixinConfig.getMchId());
        wxPayH5Config.setMchKey(weixinConfig.getMchKey());
        wxPayH5Config.setKeyPath(weixinConfig.getKeyPath());
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }
}
