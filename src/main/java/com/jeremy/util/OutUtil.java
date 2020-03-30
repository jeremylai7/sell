package com.jeremy.util;

import com.alibaba.fastjson.JSONObject;
import com.jeremy.view.Result;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 16:56
 * @Description: 输出到HttpServletResponse工具类
 */
public class OutUtil {

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        if (data != null){
            result.setData(data);
        }else {
            result.setData(new JSONObject());
        }

        return new Result(0,"成功",data);
    }
}
