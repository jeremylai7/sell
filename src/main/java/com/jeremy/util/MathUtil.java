package com.jeremy.util;

/**
 * @Auther: laizc
 * @Date: 2020/5/2 22:18
 * @Description:
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double targer,Double compare){
        Double result = Math.abs(targer - compare);
        if (result < MONEY_RANGE){
            return true;
        }
        return false;
    }
}
