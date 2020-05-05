package com.jeremy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeremy.enums.ProductStatusEnum;
import com.jeremy.util.CodeEnumUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: laizc
 * @Date: 2020/3/27 22:57
 * @Description:
 */
@Entity
@Getter
@Setter
@DynamicUpdate
public class ProductInfo{

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
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return CodeEnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }


}
