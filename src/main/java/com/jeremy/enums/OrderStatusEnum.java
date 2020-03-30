package com.jeremy.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 23:26
 * @Description: 订单状态
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"取消"),
    ;
    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;






}
