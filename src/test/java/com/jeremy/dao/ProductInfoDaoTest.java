package com.jeremy.dao;

import com.jeremy.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: laizc
 * @Date: 2020/3/27 23:13
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("14563");
        productInfo.setProductName("盖浇饭");
        productInfo.setProductPrice(new BigDecimal(18));
        productInfo.setProductStock(33);
        productInfo.setProductDescription("xxx");
        productInfo.setProductIcon("http://xx222424.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(4);
        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }



}