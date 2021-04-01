package com.gupao.designMode.proxy.staticed;

public class Pen implements Bi {
    @Override
    public void draw() {
        System.out.println("drawing****");
    }

    @Override
    public String write() {
        return "write zi ******";
    }
}
