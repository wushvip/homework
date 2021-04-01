package com.gupao.designMode.observer.subject;

import com.gupao.designMode.observer.core.Event;

public class Observer {
    public void advice(Event e){
        System.out.println("=========触发事件，打印日志========\n" + e);

        /*
         *  input
         *  input.addLisenter("click",function(){
         *
         *
         *  });
         *
         *
         * */
    }


}
