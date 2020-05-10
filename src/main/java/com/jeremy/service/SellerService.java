package com.jeremy.service;

import com.jeremy.model.SellerInfo;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 23:06
 * @Description:
 */
public interface SellerService {

    /**
     * 通过openid获取卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
