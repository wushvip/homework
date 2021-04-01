package com.gupao.factory.simple;

/**
 * j简单工厂模式（小作坊）
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        System.out.println(factory.getPhone("huaWei"));
    }
}
