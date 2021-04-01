package com.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestDecimal {

    public static void main(String[] args) {
//        int a = 3;
//        int b = 8;
//        BigDecimal decimalA = new BigDecimal(a);
//        BigDecimal decimalB = new BigDecimal(b);
//        BigDecimal result = decimalA.divide(decimalB,4,BigDecimal.ROUND_HALF_UP);
//        System.out.println(result);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        System.out.println(df.format(result));

        float c = 1f;
        float d = 3f;
        if(d !=0){
            float r = c/d;
            System.out.println(c/d);
            DecimalFormat df = new DecimalFormat("0.00%");
            System.out.println(df.format(r));
        }


        System.out.println(6/4);
    }
}
