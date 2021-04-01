package com.gupao.designMode.singleton;

public class EhanSingleton{

    public static final EhanSingleton hungry = new EhanSingleton();

    public static EhanSingleton getInstance(){
        return hungry;
    }


}
