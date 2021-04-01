package com.gupao.designMode.decotrator;

/**
 * 装饰器模式与适配器模式的区别
 * 1.装饰器          适配器
 * 满足is-a的关系    满足has-a的关系
 * 覆盖和拓展        兼容和转换
 *
 */
public class SonInterfaceimp implements SonInterface {

    private ParentInterface parentInterface;

    private SonInterfaceimp(ParentInterface parent){
        this.parentInterface = parent;
    }

    @Override
    public void speakEnglish() {
        System.out.println("english");
    }
    @Override
    public String hasCompany() {
        return parentInterface.hasCompany();
    }

    @Override
    public void speakChinese() {
        parentInterface.speakChinese();
    }


}
