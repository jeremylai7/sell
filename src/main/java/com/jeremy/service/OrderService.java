package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.exception.BusineseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 12:08
 * @Description:
 */
public interface OrderService {
    /**
     * 创建订单
     * @param alteringOrder
     * @return
     */
    AlteringOrder create(AlteringOrder alteringOrder) throws BusineseException;

    /**
     * 查询单个id
     * @param orderId
     * @return
     */
    AlteringOrder findOne(String orderId) throws BusineseException;

    /**
     * 查询订单列表
     * @param buyerOpenid 买家openid
     * @param pageable
     * @return
     */
    Page<AlteringOrder> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param alteringOrder
     * @return
     */
    AlteringOrder cancel(AlteringOrder alteringOrder) throws BusineseException;

    /**
     * 完成订单
     * @param alteringOrder
     * @return
     */
    AlteringOrder finish(AlteringOrder alteringOrder) throws BusineseException;

    /**
     * 支付订单
     * @param alteringOrder
     * @return
     */
    AlteringOrder pay(AlteringOrder alteringOrder) throws BusineseException;

    /**
     * 查询订单是否为当前操作人订单
     * @param orderId
     * @param openid
     * @return
     */
    AlteringOrder checkOrderOwner(String orderId,String openid) throws BusineseException;
}
