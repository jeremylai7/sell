package com.jeremy.util;

import com.alibaba.fastjson.JSONObject;
import com.jeremy.exception.ResponseCodes;
import com.jeremy.view.Result;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 16:56
 * @Description: 输出到HttpServletResponse工具类
 */
public class OutUtil {

    public static Result success(Object data){
        return getResult(ResponseCodes.SUCCESS,data);
    }

    public static Result error(String code,String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(new JSONObject());
        return result;
    }

    public static Result getResult(String code,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(ResponseCodes.getCodeMessage(code));
        if (data != null){
            result.setData(data);
        }else {
            result.setData(new JSONObject());
        }
        return result;
    }
}
