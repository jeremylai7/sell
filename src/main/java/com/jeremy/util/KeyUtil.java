package com.jeremy.util;

import java.util.Random;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 15:51
 * @Description:
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式： 时间戳+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + number.toString();
    }
}
