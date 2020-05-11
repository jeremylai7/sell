package com.jeremy.interceptor;

import com.jeremy.config.redis.RedisService;
import com.jeremy.constant.CookieConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: laizc
 * @Date: 2020/5/9 21:51
 * @Description: 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-----path"+request.getServletPath());
        Cookie[] cookies =request.getCookies();
        if (cookies != null){
            for(Cookie cookie:cookies){
                if (cookie.getName().equals(CookieConstant.TOKEN)){
                    String value= redisService.get("token_"+cookie.getValue());
                    if (value != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
