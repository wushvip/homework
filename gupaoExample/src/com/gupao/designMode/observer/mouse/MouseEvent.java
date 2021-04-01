package com.gupao.designMode.observer.mouse;

import com.gupao.designMode.observer.core.EventLisenter;

public class MouseEvent extends EventLisenter {

    public void click(){
        System.out.println("-----鼠标单击操作-----");
        this.trigger(MouseEnum.CLICK);
    }
}
