package com.chinamobile.rt.ws.bdoc_demo.bean;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:30
 * @Description
 * @Since V1.0
 */

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:30
 * @Description 食物的抽象
 * @Since V1.0
 */
public abstract class AbstractFood {

    private String foodName;

    private double unitPrice;

    AbstractFood(){}

    public AbstractFood(String foodName, double unitPrice){
        this.foodName = foodName;
        this.unitPrice = unitPrice;
    }

    public AbstractFood(double unitPrice){
        this.unitPrice = unitPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
