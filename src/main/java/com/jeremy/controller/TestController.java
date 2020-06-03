package com.jeremy.controller;

import com.alibaba.fastjson.JSON;
import com.jeremy.model.SellerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/5/26 23:06
 * @Description:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hello world年后";
    }


}
