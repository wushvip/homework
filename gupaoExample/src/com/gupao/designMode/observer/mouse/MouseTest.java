package com.gupao.designMode.observer.mouse;

import com.gupao.designMode.observer.core.Event;
import com.gupao.designMode.observer.core.EventLisenter;

import java.lang.reflect.Method;

public class MouseTest {
    public static void main (String[] args) throws NoSuchMethodException {

        MouseServer server = new MouseServer();
        Method method = MouseServer.class.getMethod("clickTrrige",new Class<?>[]{Event.class});

        MouseEvent event = new MouseEvent();
        event.addLisenter(MouseEnum.CLICK,server,method);

        event.click();

    }
}
