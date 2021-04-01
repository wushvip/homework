package com.test.cloneTest;
public class Father implements Cloneable{

    private double height;

    private int age;

    private ZiChan zichan;

    public Father(double height,int age,ZiChan zichan){
        this.height = height;
        this.age = age;
        this.zichan = zichan;
    }

    @Override
    protected Father clone() throws CloneNotSupportedException {
        return (Father) super.clone();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ZiChan getZichan() {
        return zichan;
    }

    public void setZichan(ZiChan zichan) {
        this.zichan = zichan;
    }
}
