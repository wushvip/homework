package com.gupao.designMode.protoType.deep;

import java.io.*;
import java.util.Date;

/**
 * 深拷贝
 */
public class JiliCar extends Car implements Cloneable,Serializable {

    public CarProduct product;

    public String shangbiao;

    public JiliCar(){}


    public JiliCar(CarProduct product, String shangbiao){
        this.product = product;
        this.shangbiao = shangbiao;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    private Object deepClone (){
        Object obj = null;
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
            baos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            JiliCar copy = (JiliCar) ois.readObject();
            copy.productDate = new Date();
            bis.close();
            ois.close();
            return copy;
        }catch(Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
