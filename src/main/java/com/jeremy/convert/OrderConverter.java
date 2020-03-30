package com.jeremy.convert;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: laizc
 * @Date: 2020/3/30 20:53
 * @Description: 订单转化
 */
public class OrderConverter {

    public static AlteringOrder convert(OrderMaster orderMaster){
        AlteringOrder alteringOrder = new AlteringOrder();
        BeanUtils.copyProperties(orderMaster,alteringOrder);
        return alteringOrder;
    }

    public static List<AlteringOrder> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());
    }
}
