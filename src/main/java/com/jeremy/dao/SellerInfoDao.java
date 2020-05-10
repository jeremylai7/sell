package com.jeremy.dao;

import com.jeremy.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 23:03
 * @Description:
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
