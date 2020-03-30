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

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<AlteringCart> alteringCartList) throws BusineseException;

    //减库存
    void decreaseStock(List<AlteringCart> alteringCartList) throws BusineseException;

}
