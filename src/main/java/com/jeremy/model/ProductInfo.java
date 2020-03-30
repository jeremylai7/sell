package com.jeremy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Auther: laizc
 * @Date: 2020/3/27 22:57
 * @Description:
 */
@Entity
@Getter
@Setter
public class ProductInfo extends BaseVo{

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
     * 商品状态
     */
    private Integer productStatus;

    /**
     * 类目编号
     */
    private Integer categoryType;


}
