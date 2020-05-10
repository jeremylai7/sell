package com.jeremy.aspect;

import com.jeremy.config.redis.RedisService;
import com.jeremy.constant.CookieConstant;
import com.jeremy.exception.SellerAuthorizeException;
import com.jeremy.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: laizc
 * @Date: 2020/5/10 11:05
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private RedisService redisService;

    @Pointcut("execution(public * com.jeremy.controller.Seller*.*(..))" +
    " && !execution(public * com.jeremy.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void before() throws SellerAuthorizeException {
        ServletRequestAttributes attributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null){
            log.warn("【登录验证】cookie中查不到token");
            throw new SellerAuthorizeException();

        }
        String redisValue = redisService.get("token_"+cookie.getValue());
        if(StringUtils.isBlank(redisValue)){
            log.warn("【登录验证】redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
