package com.graduation.sportsvenue.util;

import java.math.BigDecimal;

/**
 * 计算工具类
 */
public class BigDecimalUtils {
    /**
     * 相加
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal add(double d1,double d2){
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
        return bigDecimal1.add(bigDecimal2);
    }

    /**
     * 相乘
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal mul(double d1,double d2){
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
        return bigDecimal1.multiply(bigDecimal2);
    }



}
