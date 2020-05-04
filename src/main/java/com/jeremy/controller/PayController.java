package com.jeremy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeremy.alter.AlteringOrder;
import com.jeremy.exception.BusineseException;
import com.jeremy.exception.ResponseCodes;
import com.jeremy.service.OrderService;
import com.jeremy.service.PayService;
import com.jeremy.util.JsonUtil;
import com.jeremy.util.MathUtil;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/** 微信支付
 * @Auther: laizc
 * @Date: 2020/4/18 16:04
 * @Description:
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId, @RequestParam("returnUrl")String returnUrl) throws BusineseException {
        AlteringOrder alteringOrder = orderService.findOne(orderId);
        //订单支付
        PayResponse payResponse = payService.create(alteringOrder);
        ModelAndView view = new ModelAndView("pay/create");
        view.addObject("payResponse",payResponse);
        view.addObject("returnUrl",returnUrl);
        return view;

    }


    /**
     * 支付结果异步通知
     * @param responseData
     * @return
     * @throws BusineseException
     */
    @PostMapping("/notify")
    public ModelAndView notyfy(@RequestBody String responseData) throws BusineseException{
        payService.notyfy(responseData);
        //返回消息给微信
        System.out.println(1111);
        ModelAndView view = new ModelAndView("pay/success");
        return view;

    }




}
