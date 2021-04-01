package com.ws.desiginModel.strategy.PayPort;

import com.ws.desiginModel.strategy.PayPort.Payment;

public  class ZhifubaoPay extends AbstractPayment {
    @Override
    public void payTime() {
        System.out.println("zhifubao pay time " + System.currentTimeMillis());
    }

    @Override
    public void doTransicational() {
        System.out.println("zhifubao pay has completed");
    }
//    @Override
//    public void pay(int num,float money,String describe) {
//        System.out.println("zhifubao pay :" + money + "num:" + num + "describe:" + describe);
//    }
}
