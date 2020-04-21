package com.jeremy.service;

import com.alibaba.fastjson.JSONObject;
import com.jeremy.alter.AlteringOrder;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: laizc
 * @Date: 2020/4/18 16:11
 * @Description:
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(AlteringOrder alteringOrder) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(alteringOrder.getBuyerOpenid());
        payRequest.setOrderAmount(alteringOrder.getOrderAmount().doubleValue());
        payRequest.setOrderId(alteringOrder.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 request={}", payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 response={}",payResponse);


    }
}
