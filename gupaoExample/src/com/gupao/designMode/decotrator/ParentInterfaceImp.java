package com.gupao.designMode.decotrator;

/**
 * @Deprecated可以实现标注不在使用该类的功能
 */

//@Deprecated
public class ParentInterfaceImp implements ParentInterface{

    @Override
    public String hasCompany() {
        return "alibaba";
    }

    @Override
    public void speakChinese() {
        System.out.println("chinese");
    }
}
