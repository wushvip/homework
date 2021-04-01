package com.gupao.designMode.observer.mouse;

import com.gupao.designMode.observer.core.Event;

/**
 * 观察者
 */
public class MouseServer {

    public void clickTrrige(Event e){
        System.out.println("this is for click");
        System.out.println("=======这是鼠标单击事件=====\n" + e);
    }
}
