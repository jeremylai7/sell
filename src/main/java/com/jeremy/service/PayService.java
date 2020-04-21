package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;

/** 支付
 * @Auther: laizc
 * @Date: 2020/4/18 16:09
 * @Description:
 */
public interface PayService {

    /**
     * 发起支付
     */
    void create(AlteringOrder alteringOrder);
}
