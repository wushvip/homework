package com.gupao.designMode.decotrator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;

public class DecotratorTest {

    /**
     * 装饰者模式：是一种非常特殊的适配器模式
     * 在不修改原始类的前提下，为了实现某个类在原有类的基础上动态覆盖或者增加方法，采用装饰者模式
     *
     * @param args
     */
    public static void main(String[] args) {
        InputStream in = null;
        FilterInputStream fs = new DataInputStream(in);
//        fs.available();
    }
}
