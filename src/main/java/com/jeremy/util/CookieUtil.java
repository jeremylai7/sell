package com.jeremy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: laizc
 * @Date: 2020/5/8 22:03
 * @Description:
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name      键
     * @param value     值
     * @param maxAge    过期时间
     */
    public static void set(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = readCookieMap(request,name);
        if (cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    /**
     * cookie封装成map
     * @param request
     * @param name
     * @return
     */
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> cookieMap = new HashMap<>();
        if (cookies != null){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
