package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;
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

    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<AlteringOrder> alteringOrderPage =  orderService.findList(OPEN_ID,pageRequest);
        System.out.println(alteringOrderPage);
    }

}