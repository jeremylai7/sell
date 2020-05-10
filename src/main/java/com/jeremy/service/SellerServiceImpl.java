package com.jeremy.service;

import com.jeremy.dao.SellerInfoDao;
import com.jeremy.model.SellerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 23:07
 * @Description:
 */
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
