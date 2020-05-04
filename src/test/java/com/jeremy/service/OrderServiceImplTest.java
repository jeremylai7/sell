package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.dao.OrderMasterDao;
import com.jeremy.enums.PayStatusEnum;
import com.jeremy.exception.BusineseException;
import com.jeremy.model.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: laizc
 * @Date: 2020/3/30 21:16
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    private static final String OPEN_ID = "1234";

    private static final String ORDER_ID = "1588426474902406921";

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayService payService;

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<AlteringOrder> alteringOrderPage =  orderService.findList(OPEN_ID,pageRequest);
        System.out.println(alteringOrderPage);
    }

    @Test
    public void cancel() throws BusineseException{
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(ORDER_ID);
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMasterDao.save(orderMaster);
    }

    @Test
    public void refund() throws BusineseException {
        AlteringOrder alteringOrder = orderService.findOne("1588482849095423993");
        payService.refund(alteringOrder);
    }

    @Test
    public void list(){
        PageRequest pageRequest = new PageRequest(0,2);
        Page<AlteringOrder> page = orderService.findList(pageRequest);
        System.out.println(page);
    }





}