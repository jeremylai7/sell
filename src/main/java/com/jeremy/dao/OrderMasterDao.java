package com.jeremy.dao;

import com.jeremy.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 23:54
 * @Description:
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String openid,Pageable pageable);
}
