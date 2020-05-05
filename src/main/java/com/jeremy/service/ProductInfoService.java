package com.jeremy.service;

import com.jeremy.alter.AlteringCart;
import com.jeremy.exception.BusineseException;
import com.jeremy.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 15:31
 * @Description: 商品
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 新增、修改
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<AlteringCart> alteringCartList) throws BusineseException;

    //减库存
    void decreaseStock(List<AlteringCart> alteringCartList) throws BusineseException;

    /**
     * 上架
     * @param productId 商品id
     */
    void onSale(String productId) throws BusineseException;

    /**
     * 下架
     * @param productId  商品id
     */
    void offSale(String productId) throws BusineseException;

}
