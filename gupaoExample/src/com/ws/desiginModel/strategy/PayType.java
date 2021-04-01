package com.ws.desiginModel.strategy;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.ws.desiginModel.strategy.PayPort.Payment;
import com.ws.desiginModel.strategy.PayPort.WeixinPay;
import com.ws.desiginModel.strategy.PayPort.ZhifubaoPay;

public enum PayType {
//    ZHIFUBAO(new ZhifubaoPay());
    ZHFUBAO(new ZhifubaoPay()),
    WECHAT(new WeixinPay());


    private Payment payment;
    PayType(Payment payment){
        this.payment = payment;
    }

    public Payment get(){
        return this.payment;
    }
}
