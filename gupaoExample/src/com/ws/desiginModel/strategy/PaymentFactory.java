package com.ws.desiginModel.strategy;

import com.ws.desiginModel.strategy.PayPort.Payment;
import com.ws.desiginModel.strategy.PayPort.WeixinPay;
import com.ws.desiginModel.strategy.PayPort.ZhifubaoPay;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {
    private static Map<String,Object> map = new HashMap<>();




    public static Payment getPayMethod(String payType){
        Payment payMent = null;
        switch (payType){
            case "weixin":
                payMent =  new WeixinPay();
                break;
            case "zhifubao":
                payMent = new ZhifubaoPay();
                break;
                default: return null;
        }
        return payMent;
    }
}
