package com.ws.desiginModel.strategy;

import com.ws.desiginModel.strategy.PayPort.AbstractPayment;

/**
 * 工厂模式 + 策略模式
 */
public class Test {
    public static void main(String[] args) {
        Order order = new Order(5,3.5f,"pen");
//        order.pay(PayType.ZHFUBAO.get());

        order.pay(PaymentFactory.getPayMethod("weixin"));


//        order.pay(AbstractPayment.map.get("weixin"));
    }
}
