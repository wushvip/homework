package com.ws.desiginModel.strategy;

import com.ws.desiginModel.strategy.PayPort.Payment;

public class Order {
    private int num;

    private float price;

    private String descibe;

    public Order(int num,float price,String descibe){
        this.num = num;
        this.price = price;
        this.descibe = descibe;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescibe() {
        return descibe;
    }

    public void setDescibe(String descibe) {
        this.descibe = descibe;
    }

    public void pay(Payment payment){
        payment.pay(this.num,this.price,this.descibe);
    }
}
