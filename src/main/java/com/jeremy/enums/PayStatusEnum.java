package com.jeremy.enums;

import lombok.Getter;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 23:34
 * @Description: 订单状态
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    PayStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;




}
