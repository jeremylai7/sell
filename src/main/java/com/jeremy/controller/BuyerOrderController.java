package com.jeremy.controller;

import com.alibaba.fastjson.JSONObject;
import com.jeremy.alter.AlteringOrder;
import com.jeremy.convert.OrderFormConverter;
import com.jeremy.exception.BusineseException;
import com.jeremy.exception.ResponseCodes;
import com.jeremy.form.OrderForm;
import com.jeremy.service.OrderService;
import com.jeremy.util.OutUtil;
import com.jeremy.view.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: laizc
 * @Date: 2020/3/30 23:34
 * @Description: 买家订单
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;


    //创建订单
    @PostMapping("/create")
    public Result create(@Valid OrderForm orderForm, BindingResult bindingResult) throws BusineseException {
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new BusineseException(ResponseCodes.PARAM_ERROR,bindingResult.getFieldError().getDefaultMessage());
        }
        AlteringOrder alteringOrder = OrderFormConverter.converter(orderForm);
        AlteringOrder order = orderService.create(alteringOrder);
        if (CollectionUtils.isEmpty(order.getOrderDetailList())){
            throw new BusineseException(ResponseCodes.CAR_EMPTY);
        }
        JSONObject json = new JSONObject();
        json.put("orderId",order.getOrderId());
        return OutUtil.success(json);
    }

    //订单列表
    @GetMapping("/list")
    public Result list(@RequestParam("openid") String openid,
                        @RequestParam(value = "page",defaultValue = "0") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size) throws BusineseException {
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单】 openid为空");
            throw new BusineseException(ResponseCodes.PARAM_ERROR);
        }
        PageRequest pageable = new PageRequest(page,size);
        Page<AlteringOrder> alteringOrderPage = orderService.findList(openid,pageable);
        return OutUtil.success(alteringOrderPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public Result detail(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId) throws BusineseException {
        AlteringOrder alteringOrder = orderService.checkOrderOwner(orderId,openid);
        return OutUtil.success(alteringOrder);
    }

    //取消订单
    @PostMapping("/cancel")
    public Result cancel(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId) throws BusineseException {
        AlteringOrder alteringOrder = orderService.checkOrderOwner(orderId,openid);
        if (alteringOrder == null){
            log.error("【取消订单】 订单不存在 orderId={}",orderId);
            throw new BusineseException(ResponseCodes.ORDER_NOT_EXIST);
        }
        orderService.cancel(alteringOrder);
        return OutUtil.success(null);
    }

}
