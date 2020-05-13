package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;

/**
 * @Auther: laizc
 * @Date: 2020/5/10 21:03
 * @Description: 微信消息推送
 */
public interface PushMessageService {

    /**
     * 订单状态变成发送消息
     * @param alteringOrder
     */
    void orderStatus(AlteringOrder alteringOrder);
}
