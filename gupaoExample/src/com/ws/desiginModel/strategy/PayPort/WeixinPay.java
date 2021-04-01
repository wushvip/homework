package com.ws.desiginModel.strategy.PayPort;

import com.ws.desiginModel.strategy.PayPort.Payment;

public class WeixinPay extends AbstractPayment {


    @Override
    public void payTime() {
        System.out.println("weixin pay time " + System.currentTimeMillis());
    }

    @Override
    public void doTransicational() {
        System.out.println("weixin pay has completed");
    }
}
