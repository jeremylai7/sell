package com.jeremy.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 16:14
 * @Description:
 */
@Getter
@Setter
public class ProductForm {
    @Id
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
