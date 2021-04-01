package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        String[] intarrs = {"1","2","3","4","5","6","-1","8","9"};
        List<String> list = Arrays.asList(intarrs);


    }
    public void testBdocException(List<String> list){
        list.forEach((i)->{
            if(Integer.parseInt(i)<0){
                try {
                    throw new Exception("当前数字是负数");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(i);
        });

    }
}
