package com.test.cloneTest;

/**
 * clone时，被复制对象---父类，复制结果---子类
 * 当被复制对象中含有引用时，复制副本中引用类型和父类中的引用一样，当复制副本中改变此引用中对象时，父类也会随之更改。（clone时的隐患）
 */
public class TestClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        ZiChan money = new ZiChan();
        money.setMoney(10000000);
        Father father = new Father(1.8,31,money);
        System.out.println("father:" + father);
        System.out.println("father money" + father.getZichan().getMoney());
        Father son = father.clone();
        System.out.println("son:" + son);
        son.getZichan().setMoney(99999999);
        System.out.println("son money:" + son.getZichan());

        System.out.println("father money ------" + father.getZichan().getMoney());
    }
}
