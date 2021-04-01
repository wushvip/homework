package com.gupao.designMode.observer.subject;

import com.gupao.designMode.observer.core.Event;

import java.lang.reflect.Method;

public class ObserverTest {

    public static void main(String[] args) {
        try{

            //观察者
            Observer observer = new Observer();
            Method advice = Observer.class.getMethod("advice", new Class<?>[]{Event.class});


            //这里写Lily
            Subject subject = new Subject();
            subject.addLisenter(SubjectEventType.ON_ADD,observer,advice);
//            subject.addLisenter(SubjectEventType.ON_EDIT,observer,advice);
//            subject.addLisenter(SubjectEventType.ON_RMOVE,observer,advice);
//            subject.addLisenter(SubjectEventType.ON_QUERY,observer,advice);

            subject.add();
            subject.remove();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
