package com.jeremy.controller;

import com.jeremy.config.WechatMpConfig;
import com.jeremy.view.ViewProduct;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Auther: laizc
 * @Date: 2020/4/12 14:46
 * @Description:
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl) throws UnsupportedEncodingException {
        String url = "http://selles.natapp1.cc/sell/wechat/info?returnUrl="+returnUrl;
        String redictUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl,"UTF-8"));
        return "redirect:"+redictUrl;
    }

    @GetMapping("/info")
    public String info(@RequestParam("code")String code,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信授权登录】error"+e.getError().getErrorMsg());
            e.printStackTrace();
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl + "?openid="+openid;
    }
}
