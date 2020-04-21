package com.jeremy.alter;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 16:35
 * @Description: 购物车传送模型
 */
@Getter
@Setter
public class AlteringCart {

    private Integer productQuantity;

    private String productId;

    public AlteringCart(String productId,Integer productQuantity) {
        this.productQuantity = productQuantity;
        this.productId = productId;
    }
}
