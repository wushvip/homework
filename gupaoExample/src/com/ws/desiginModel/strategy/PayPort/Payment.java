package com.ws.desiginModel.strategy.PayPort;

public interface Payment {
    public void pay(int num,float money,String descire);

    public String payType(int id);
}
