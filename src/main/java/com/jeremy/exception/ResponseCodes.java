package com.jeremy.exception;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Auther: laizc
 * @Date: 2020/3/29 15:23
 * @Description:
 */
public class ResponseCodes {
    private static ResourceBundle bundle;

    //成功
    public static final String SUCCESS = "0";

    //失败
    public static final String FAIL = "-1";

    //商品不存在
    public static final String PRODUCT_NOT_EXIST = "-2";

    //库存不足
    public static final String PRODUCT_UNDER_STOCK = "-3";

    //订单不存在
    public static final String ORDER_NOT_EXIST = "-4";

    //订单详情不存在
    public static final String ORDERDETAIL_NOT_EXIST = "-5";

    //订单状态不正确
    public static final String ORDER_STATUS_ERROR = "-6";

    //订单取消失败
    public static final String ORDER_UPDATE_FAIL = "-7";

    //订单详情为空
    public static final String ORDER_DETAIL_EMPTY = "-8";

    //订单支付状态不正确
    public static final String ORDER_PAY_STATUS_ERROR = "-9";

    //参数不正确
    public static final String PARAM_ERROR = "-10";

    //购物车不能为空
    public static final String CAR_EMPTY = "-11";

    public static final String ORDER_OWNER_ERROR = "-12";

    public static String getCodeMessage(String code){
        if (bundle == null){
            bundle = loadProperties();
        }
        return bundle.getString(code);
    }

    private static ResourceBundle loadProperties(){
        return ResourceBundle.getBundle("", Locale.getDefault());
    }
}
