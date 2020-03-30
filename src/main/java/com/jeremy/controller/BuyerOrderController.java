package com.jeremy.controller;

import com.jeremy.service.OrderService;
import com.jeremy.util.OutUtil;
import com.jeremy.view.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: laizc
 * @Date: 2020/3/30 23:34
 * @Description: 买家订单
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;


    public Result create(){
        return OutUtil.success(null);
    }

}
