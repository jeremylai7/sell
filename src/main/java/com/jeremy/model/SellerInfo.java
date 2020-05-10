package com.jeremy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 23:02
 * @Description: 卖家信息
 */
@Getter
@Setter
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
