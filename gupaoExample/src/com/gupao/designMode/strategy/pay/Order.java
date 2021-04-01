package com.gupao.designMode.strategy.pay;


import com.gupao.designMode.strategy.pay.payport.PayType;
import com.gupao.designMode.strategy.pay.payport.Payment;

/**
 * Created by Tom on 2018/3/11.
 */
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid,String orderId,double amount){
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }


    //这个参数，完全可以用Payment这个接口来代替
    //为什么？

    //完美地解决了switch的过程，不需要在代码逻辑中写switch了
    //更不需要写if    else if
//    public PayState pay(PayType payType){
//        return payType.get().pay(this.uid,this.amount);
//    }
    public PayState pay(Payment payment){
        return payment.pay(this.uid,this.amount);
    }
}
