package com.gupao.designMode.singleton.seriable;

import java.io.Serializable;

public class Seriable implements Serializable {

    public static final Seriable seriable = new Seriable();

    private Seriable(){}

    public static Seriable getInstance(){
        return seriable;
    }

    /**
     * 增加此方法，可以保证反序列化时，不会破坏单例，产生新的对象
     * @return
     */
    public Object readResolve(){
        return seriable;
    }
}
