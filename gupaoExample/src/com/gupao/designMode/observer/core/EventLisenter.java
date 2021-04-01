package com.gupao.designMode.observer.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventLisenter {
    //Map相当于是一个注册器
    protected Map<Enum,Event> events = new HashMap<Enum,Event>();

    public void addLisenter(Enum eventType,Object target,Method callback){
        //注册事件
        //用反射调用这个方法
        events.put(eventType,new Event(target,callback));
    }

    private void trigger(Event e){
        e.setSource(this);
        e.setTime(System.currentTimeMillis());

        try {
            e.getCallback().invoke(e.getTarget(),e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    protected void trigger(Enum call){
        if(!events.containsKey(call)){ return ;}
        trigger(events.get(call).setTrigger(call.toString()));
    }

}
