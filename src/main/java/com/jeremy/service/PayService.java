package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.exception.BusineseException;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/** 支付
 * @Auther: laizc
 * @Date: 2020/4/18 16:09
 * @Description:
 */
public interface PayService {

    /**
     * 发起支付
     */
    PayResponse create(AlteringOrder alteringOrder);

    /**
     * 支付结果通知
     * @param responseData
     */
    PayResponse notyfy(String responseData) throws BusineseException;

    /**
     * 退款
     * @param alteringOrder
     */
    RefundResponse refund(AlteringOrder alteringOrder);
}
