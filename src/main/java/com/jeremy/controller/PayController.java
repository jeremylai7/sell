package com.jeremy.controller;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.exception.BusineseException;
import com.jeremy.service.OrderService;
import com.jeremy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** 微信支付
 * @Auther: laizc
 * @Date: 2020/4/18 16:04
 * @Description:
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId, @RequestParam("returnUrl")String returnUrl) throws BusineseException {
        AlteringOrder alteringOrder = orderService.findOne(orderId);
        //订单支付
        //payService.create(alteringOrder);
        return new ModelAndView("pay/create");

    }

}
