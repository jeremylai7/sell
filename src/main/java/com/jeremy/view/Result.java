package com.jeremy.view;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 16:32
 * @Description:
 */
@Getter
@Setter
public class Result {
    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    private Object data;

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
