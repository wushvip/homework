package com.gupao.designMode.singleton;

public class LazySingleton {
    private static LazySingleton lazy;

    public  static synchronized LazySingleton getInstance(){
        if(lazy == null){
            lazy = new LazySingleton();
        }
        return lazy;
    }

}
