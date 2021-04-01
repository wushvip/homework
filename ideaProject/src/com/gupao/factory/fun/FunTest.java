package com.gupao.factory.fun;

import com.gupao.factory.Phone;

/**
 * 工厂方法模式
 */
public class FunTest{
    public static void main(String[] args) {
        FunFactory factory = new HuaWeiFactory();
        System.out.println(factory.getPhone());
    }
}
