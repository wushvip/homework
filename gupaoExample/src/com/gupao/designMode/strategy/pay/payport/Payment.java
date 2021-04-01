package com.gupao.designMode.strategy.pay.payport;

import com.gupao.designMode.strategy.pay.PayState;

/**
 * 支付渠道
 * Created by Tom on 2018/3/11.
 */
public interface Payment {

    public PayState pay(String uid, double amount);

}
