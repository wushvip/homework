package com.gupao.designMode.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private BeanFactory(){}

    private static Map<String,Object> ioc = new ConcurrentHashMap<String,Object>();

    public static Object getBean(String className){
        if(!ioc.containsKey(className)){
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className,obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }else{
            return ioc.get(className);
        }

//        if(!ioc.containsKey(className)){
//            Object obj = null;
//            try{
//                obj = Class.forName(className).newInstance();
//                ioc.put(className,obj);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            return  obj;
//        }else{
//            return ioc.get(className);
//        }

//        Object obj = null;
//        try{
//            if(ioc.containsKey(className)){
//                obj = ioc.get(className);
//            }else{
//               obj = Class.forName(className).newInstance();
//                ioc.put(className,obj);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return obj;
    }

}
