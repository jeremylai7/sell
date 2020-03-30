package com.jeremy.enums;

import lombok.Getter;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 15:47
 * @Description: 商品状态
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架")

    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
