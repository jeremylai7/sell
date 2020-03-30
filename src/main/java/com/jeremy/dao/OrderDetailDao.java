package com.jeremy.dao;

import com.jeremy.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 23:56
 * @Description:
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);
}
