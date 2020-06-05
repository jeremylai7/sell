package com.jeremy.service;

import com.jeremy.alter.AlteringCart;
import com.jeremy.dao.ProductInfoDao;
import com.jeremy.enums.ProductStatusEnum;
import com.jeremy.exception.BusineseException;
import com.jeremy.exception.ResponseCodes;
import com.jeremy.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 15:35
 * @Description:
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        /**
         * 如果存在id返回信息，否则返回null
         * .get() 抛异常
         */
        ProductInfo productInfo = productInfoDao.findById(productId).orElse(null);
        return productInfo;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseStock(List<AlteringCart> alteringCartList) throws BusineseException {
        for(AlteringCart alteringCart : alteringCartList){
            ProductInfo productInfo = productInfoDao.findById(alteringCart.getProductId()).orElse(null);
            if (productInfo == null){
                throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
            }
            Integer add = alteringCart.getProductQuantity() + productInfo.getProductStock();
            productInfo.setProductStock(add);
            productInfoDao.save(productInfo);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<AlteringCart> alteringCartList) throws BusineseException {
        for(AlteringCart alteringCart : alteringCartList){
            ProductInfo productInfo = productInfoDao.findById(alteringCart.getProductId()).orElse(null);
            if (productInfo == null){
                throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
            }
            Integer subPrductStock = productInfo.getProductStock() - alteringCart.getProductQuantity();
            if (subPrductStock < 0){
                //库存不足
                throw new BusineseException(ResponseCodes.PRODUCT_UNDER_STOCK);
            }
            productInfo.setProductStock(subPrductStock);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    public void onSale(String productId) throws BusineseException {
        ProductInfo productInfo = productInfoDao.findById(productId).orElse(null);
        if (productInfo == null){
            throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new BusineseException(ResponseCodes.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoDao.save(productInfo);
    }

    @Override
    public void offSale(String productId) throws BusineseException {
        ProductInfo productInfo = productInfoDao.findById(productId).orElse(null);
        if (productInfo == null){
            throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new BusineseException(ResponseCodes.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoDao.save(productInfo);
    }
}
