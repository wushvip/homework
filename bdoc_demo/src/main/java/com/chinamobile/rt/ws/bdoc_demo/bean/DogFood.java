package com.chinamobile.rt.ws.bdoc_demo.bean;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:32
 * @Description
 * @Since V1.0
 */

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:32
 * @Description
 * @Since V1.0
 */
public class DogFood extends AbstractFood {

    private String foodName;

    public DogFood(String foodName,double unitPrice){
        super(foodName,unitPrice);
        this.foodName = foodName;
    }
}
