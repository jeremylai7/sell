package com.jeremy.service;

import com.jeremy.alter.AlteringCart;
import com.jeremy.alter.AlteringOrder;
import com.jeremy.convert.OrderConverter;
import com.jeremy.dao.OrderDetailDao;
import com.jeremy.dao.OrderMasterDao;
import com.jeremy.enums.OrderStatusEnum;
import com.jeremy.enums.PayStatusEnum;
import com.jeremy.exception.BusineseException;
import com.jeremy.exception.ResponseCodes;
import com.jeremy.model.OrderDetail;
import com.jeremy.model.OrderMaster;
import com.jeremy.model.ProductInfo;
import com.jeremy.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 12:14
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public AlteringOrder create(AlteringOrder alteringOrder) throws BusineseException {
        BigDecimal amount = BigDecimal.ZERO;
        String orderId = KeyUtil.getUniqueKey();
        //查询商品、数量、计算总价
        for (OrderDetail orderDetail : alteringOrder.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                //商品不存在
                throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
            }
            BeanUtils.copyProperties(productInfo, orderDetail);
            //总价计算
            amount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(amount);
            //订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetailDao.save(orderDetail);
        }
        //写入订单主表数据
        OrderMaster orderMaster = new OrderMaster();
        alteringOrder.setOrderId(orderId);
        BeanUtils.copyProperties(alteringOrder,orderMaster);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);
        //减库存
        List<AlteringCart> cartList =alteringOrder.getOrderDetailList().stream().map(e ->
            new AlteringCart(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartList);
        return alteringOrder;
    }

    @Override
    public AlteringOrder findOne(String orderId) throws BusineseException {
        OrderMaster orderMaster =orderMasterDao.findOne(orderId);
        if (orderMaster == null){
            //订单不存在
            throw new BusineseException(ResponseCodes.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetail = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetail)){
            //订单详情不存在
            throw new BusineseException(ResponseCodes.ORDERDETAIL_NOT_EXIST);
        }
        AlteringOrder alteringOrder = new AlteringOrder();
        BeanUtils.copyProperties(orderMaster,alteringOrder);
        alteringOrder.setOrderDetailList(orderDetail);
        return alteringOrder;
    }

    @Override
    public Page<AlteringOrder> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterspage = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<AlteringOrder> alteringOrderList = OrderConverter.convert(orderMasterspage.getContent());
        return new PageImpl<>(alteringOrderList,pageable,orderMasterspage.getTotalElements());
    }

    @Override
    @Transactional
    public AlteringOrder cancel(AlteringOrder alteringOrder) throws BusineseException {
        //判断订单状态
        if (!alteringOrder.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId = {},orderStatus = {}",alteringOrder.getOrderId(),alteringOrder.getOrderStatus());
            throw new BusineseException(ResponseCodes.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        alteringOrder.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(alteringOrder,orderMaster);
        OrderMaster orderMasterUpdate = orderMasterDao.save(orderMaster);
        if (orderMasterUpdate == null){
            throw new BusineseException(ResponseCodes.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(alteringOrder.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情");
            throw new BusineseException(ResponseCodes.ORDER_DETAIL_EMPTY);
        }
        List<AlteringCart> alteringCartList =alteringOrder.getOrderDetailList().stream().map(e->
            new AlteringCart(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(alteringCartList);
        //如果支付，退款
        if (alteringOrder.getPayStatus() == PayStatusEnum.SUCCESS.getCode()){
            payService.refund(alteringOrder);
        }
        return alteringOrder;
    }

    @Override
    @Transactional
    public AlteringOrder finish(AlteringOrder alteringOrder) throws BusineseException {
        //判断订单状态
        if (!alteringOrder.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】 订单状态不正确，orderId={},orderStatus={}",alteringOrder.getOrderId(),alteringOrder.getOrderStatus());
            throw new BusineseException(ResponseCodes.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        alteringOrder.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(alteringOrder,orderMaster);
        orderMaster.setOrderId(alteringOrder.getOrderId());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMasterUpdate = orderMasterDao.save(orderMaster);
        if (orderMasterUpdate == null){
            log.error("【完结订单】更新失败，orderId={},orderStatus={}",alteringOrder.getOrderId(),alteringOrder.getOrderStatus());
            throw new BusineseException(ResponseCodes.ORDER_UPDATE_FAIL);
        }
        return alteringOrder;
    }

    @Override
    @Transactional
    public AlteringOrder pay(AlteringOrder alteringOrder) throws BusineseException {
        //判断订单状态
        if(!alteringOrder.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付】订单状态不正确，orderId={},orderStatus={}",alteringOrder.getOrderId(),alteringOrder.getOrderStatus());
            throw new BusineseException(ResponseCodes.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!alteringOrder.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】订单支付状态不正确，orderId={},payStatus={}",alteringOrder.getOrderId(),alteringOrder.getPayStatus());
            throw new BusineseException(ResponseCodes.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        alteringOrder.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(alteringOrder,orderMaster);
        orderMaster.setOrderId(alteringOrder.getOrderId());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMasterUpdate = orderMasterDao.save(orderMaster);
        if (orderMasterUpdate == null){
            log.error("【订单支付】更新失败，orderId={},payStatus={}",alteringOrder.getOrderId(),alteringOrder.getPayStatus());
            throw new BusineseException(ResponseCodes.ORDER_UPDATE_FAIL);
        }
        return alteringOrder;
    }

    @Override
    public AlteringOrder checkOrderOwner(String orderId, String openid) throws BusineseException {
        AlteringOrder order = this.findOne(orderId);
        if (!order.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】 订单的openid不一致,orderId={},openid={}",orderId,openid);
            throw new BusineseException(ResponseCodes.ORDER_OWNER_ERROR);
        }
        return order;
    }

    @Override
    public Page<AlteringOrder> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);
        List<AlteringOrder> alteringOrderList = OrderConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(alteringOrderList,pageable,orderMasterPage.getTotalElements());
    }
}
