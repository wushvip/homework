package com.ws.desiginModel.strategy.PayPort;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractPayment implements Payment{


//    public static ConcurrentHashMap<String,Payment> map = new ConcurrentHashMap<>();

//    @PostConstruct
//    public void init(String type){
//        map.put(type,this);
//    }


    @Override
    public void pay(int num, float money, String describe) {
        payTime();
        System.out.println("pay num:" + num + "money: " + money + "describe: " + describe);
        doTransicational();
    }

    @Override
    public String payType(int id) {
        return null;
    }

    public abstract void payTime();

    public abstract void doTransicational();
}
