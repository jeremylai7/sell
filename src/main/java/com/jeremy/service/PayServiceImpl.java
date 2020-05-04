package com.jeremy.service;

import com.alibaba.fastjson.JSONObject;
import com.jeremy.alter.AlteringOrder;
import com.jeremy.exception.BusineseException;
import com.jeremy.exception.ResponseCodes;
import com.jeremy.util.JsonUtil;
import com.jeremy.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
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

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(AlteringOrder alteringOrder) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(alteringOrder.getBuyerOpenid());
        payRequest.setOrderAmount(alteringOrder.getOrderAmount().doubleValue());
        payRequest.setOrderId(alteringOrder.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 response={}",JsonUtil.toJson(payResponse));
        return payResponse;

    }

    @Override
    public PayResponse notyfy(String responseData) throws BusineseException {
        PayResponse payResponse = bestPayService.asyncNotify(responseData);
        log.info("【微信支付】 异步通知,response ={}",JsonUtil.toJson(payResponse));
        AlteringOrder alteringOrder = orderService.findOne(payResponse.getOrderId());
        //校验算法
        //校验支付状态
        //校验金额
        //支付人和下单人是否一致
        if (alteringOrder == null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",alteringOrder.getOrderId());
            throw new BusineseException(ResponseCodes.ORDERDETAIL_NOT_EXIST);
        }
        //校验金额
        if (!MathUtil.equals(alteringOrder.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    alteringOrder.getOrderAmount());
        }
        //修改订单状态
        orderService.pay(alteringOrder);
        return payResponse;
    }

    @Override
    public RefundResponse refund(AlteringOrder alteringOrder) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        refundRequest.setOrderId(alteringOrder.getOrderId());
        refundRequest.setOrderAmount(alteringOrder.getOrderAmount().doubleValue());
        log.info("【微信退款】 request={}",refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 response={}",refundResponse);
        return refundResponse;
    }
}
