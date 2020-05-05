package com.jeremy.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: laizc
 * @Date: 2020/3/26 22:36
 * @Description:
 */
@Getter
@Setter
@Entity
//动态更新
@DynamicUpdate
public class ProductCategory{

    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String  categoryName;

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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


}
