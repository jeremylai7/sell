package com.jeremy.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


/**
 * @Auther: laizc
 * @Date: 2020/3/31 20:39
 * @Description:
 */
@Getter
@Setter
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}
