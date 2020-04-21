package com.jeremy.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jeremy.alter.AlteringCart;
import com.jeremy.alter.AlteringOrder;
import com.jeremy.form.OrderForm;
import com.jeremy.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/31 21:04
 * @Description:
 */
public class OrderFormConverter {

    public static AlteringOrder converter(OrderForm orderForm){
        AlteringOrder alteringOrder = new AlteringOrder();
        alteringOrder.setBuyerPhone(orderForm.getPhone());
        alteringOrder.setBuyerName(orderForm.getName());
        alteringOrder.setBuyerAddress(orderForm.getAddress());
        alteringOrder.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = JSON.parseArray(orderForm.getItems(),OrderDetail.class);
        alteringOrder.setOrderDetailList(orderDetailList);
        return alteringOrder;
    }
}
