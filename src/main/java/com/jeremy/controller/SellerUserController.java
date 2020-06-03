package com.jeremy.controller;

import com.jeremy.config.ProjectUrlConfig;
import com.jeremy.config.redis.RedisService;
import com.jeremy.constant.CookieConstant;
import com.jeremy.model.SellerInfo;
import com.jeremy.service.SellerService;
import com.jeremy.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Auther: laizc
 * @Date: 2020/5/7 22:54
 * @Description: 买家用户
 */
@CrossOrigin
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 登录
     * @param openid
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse response){
        //查询openid
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            ModelAndView view = new ModelAndView("common/error");
            view.addObject("msg","登录openid不存在");
            view.addObject("url","/sell/seller/order/list");
            return view;
        }
        //设置token到redis
        String token = UUID.randomUUID().toString();
        redisService.set("token_"+token,openid,7200);
        //设置cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return new ModelAndView("redirect:"+ projectUrlConfig.getSell() +"/sell/seller/order/list");


    }

    /**
     * 登出
     * @param request
     * @param response
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
        //查询cookie
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie != null){
            //清除redis
            redisService.remove("token_"+cookie.getValue());
            //清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        ModelAndView view = new ModelAndView("common/success");
        view.addObject("msg","登出成功");
        view.addObject("url","/sell/seller/order/list");
        return view;
    }
}
