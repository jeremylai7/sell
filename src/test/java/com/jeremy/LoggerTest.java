package com.jeremy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: laizc
 * @Date: 2018/8/26 14:34
 * @Description: 日志测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1(){
        log.info("info...");
        log.debug("debug....");
        log.error("error----");
        String name = "admin";
        String password = "1234";
        log.info("name:{},password {}",name,password);


    }

}
