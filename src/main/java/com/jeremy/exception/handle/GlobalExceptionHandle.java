package com.jeremy.exception.handle;

import com.jeremy.config.ProjectUrlConfig;
import com.jeremy.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: laizc
 * @Date: 2020/5/10 15:27
 * @Description: 全局异常捕获
 */
@ControllerAdvice
public class GlobalExceptionHandle {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 登录异常处理
     * @return
     */
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView sellerAuthorizeHandle(){
        //TODO 暂时跳转登录页面
        String url = "redirect:https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http%3A%2F%2Fsell.springboot.cn%2Fsell%2Fqr%2FoTgZpwa6RG_iVa0_MKgyrMrDnGT0&response_type=code&scope=snsapi_login&state=http%3A%2F%2Fselles.natapp1.cc%2Fsell%2Fwechat%2Fqr-info";
        /*String url ="redirect:".concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qr-authorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login");*/
        ModelAndView view = new ModelAndView(url);
        return view;
    }
}
