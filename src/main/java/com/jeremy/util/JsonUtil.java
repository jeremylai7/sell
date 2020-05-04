package com.jeremy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Auther: laizc
 * @Date: 2020/5/2 12:18
 * @Description: 返回json格式数据
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
