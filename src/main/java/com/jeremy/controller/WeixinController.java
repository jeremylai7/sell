package com.jeremy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeremy.config.WeixinConfig;
import com.jeremy.util.OutUtil;
import com.jeremy.view.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @Auther: laizc
 * @Date: 2020/4/12 00:12
 * @Description:
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @Autowired
    private WeixinConfig weixinConfig;

    @GetMapping("/auth")
    public Result auth(@RequestParam("code")String code){
        log.info(code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+weixinConfig.getMpAppId()+"&secret="+weixinConfig.getMpSecretKey()+"&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        System.out.println(response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        //System.out.println(jsonObject);
        String token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        //拉取用户信息
        String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token="+token+"&openid="+openid+"&lang=zh_CN";
        RestTemplate restTemplate2 = new RestTemplate();
        restTemplate2.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String response2 = restTemplate2.getForObject(url1,String.class);
        System.out.println(response2);

        return OutUtil.success(null);


    }
}
