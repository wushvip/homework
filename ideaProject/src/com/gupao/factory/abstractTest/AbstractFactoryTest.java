package com.gupao.factory.abstractTest;

/**
 * 抽象工厂模式
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        PhoFactory factory = new PhoFactory();
        System.out.println(factory.getXiaoMi());
    }
}
