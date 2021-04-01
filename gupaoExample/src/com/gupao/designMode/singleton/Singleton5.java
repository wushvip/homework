package com.gupao.designMode.singleton;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * c存储器模式
 */
public class Singleton5 {

    private static Map<String,Object> map = new HashMap<String,Object>();


    public static Object getInstance(String key){
        if(map.get(key) == null){
            map.put(key,new Singleton5());
        }
        return map.get(key);
    }

}
