package com.jeremy.exception;

import lombok.Getter;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 13:13
 * @Description: 业务异常
 */
@Getter
public class BusineseException extends Exception{

    public BusineseException(String code,String... args){
        this.code = code;
        this.args = args;
    }
    private String code;

    private String[] args;
}
