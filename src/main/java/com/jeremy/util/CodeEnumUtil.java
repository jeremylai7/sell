package com.jeremy.util;

import com.jeremy.enums.BaseCodeEnum;

/**
 * @Auther: laizc
 * @Date: 2020/5/4 09:48
 * @Description: 枚举工具类
 */
public class CodeEnumUtil {

    /**
     *根据code获取枚举
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends BaseCodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T t:enumClass.getEnumConstants()){
            if (code.equals(t.getCode())){
                return t;
            }
        }
        return null;
    }
}
