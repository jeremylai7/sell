package com.jeremy.alter;

import com.jeremy.enums.OrderStatusEnum;
import com.jeremy.enums.PayStatusEnum;
import com.jeremy.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 11:55
 * @Description: 订单传输模型
 */
@Getter
@Setter
public class AlteringOrder {
    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     * 买家号码
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 默认新建
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态 默认未支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private List<OrderDetail> orderDetailList;
}
