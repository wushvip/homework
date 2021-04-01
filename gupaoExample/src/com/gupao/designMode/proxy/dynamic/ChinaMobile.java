package com.gupao.designMode.proxy.dynamic;

public class ChinaMobile implements QiYe {
    @Override
    public ChengXuYuan find() {
        ChengXuYuan cxy = new ChengXuYuan();
        cxy.setHeight(1.8f);
        cxy.setWeight(75);
        System.out.println("find chengxuyuan" + cxy);
        return cxy ;
    }
}
