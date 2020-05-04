package com.jeremy.exception;

import lombok.Getter;

import java.text.MessageFormat;

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

    @Override
    public String getMessage(){
        String message =  ResponseCodes.getCodeMessage(code);
        if (args == null){
            return message;
        }
        return MessageFormat.format(message,args);
    }


}
