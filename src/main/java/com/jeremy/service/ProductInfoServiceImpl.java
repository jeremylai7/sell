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
        return productInfoDao.findOne(productId);
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
    @Transactional
    public void increaseStock(List<AlteringCart> alteringCartList) throws BusineseException {
        for(AlteringCart alteringCart : alteringCartList){
            ProductInfo productInfo = productInfoDao.findOne(alteringCart.getProductId());
            if (productInfo == null){
                throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
            }
            Integer add = alteringCart.getProductStock() + productInfo.getProductStock();
            productInfo.setProductStock(add);
            productInfoDao.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<AlteringCart> alteringCartList) throws BusineseException {
        for(AlteringCart alteringCart : alteringCartList){
            ProductInfo productInfo = productInfoDao.findOne(alteringCart.getProductId());
            if (productInfo == null){
                throw new BusineseException(ResponseCodes.PRODUCT_NOT_EXIST);
            }
            Integer subPrductStock = productInfo.getProductStock() - alteringCart.getProductStock();
            if (subPrductStock < 0){
                //库存不足
                throw new BusineseException(ResponseCodes.PRODUCT_UNDER_STOCK);
            }
            productInfo.setProductStock(subPrductStock);
            productInfoDao.save(productInfo);
        }
    }
}
