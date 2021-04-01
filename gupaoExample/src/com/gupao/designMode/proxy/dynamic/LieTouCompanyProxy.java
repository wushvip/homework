package com.gupao.designMode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LieTouCompanyProxy implements InvocationHandler {

    private Object target;

    public LieTouCompanyProxy(Object target){
        this.target = target;
    }

    public Object getInstance(){
        Class clazz = target.getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        Class[] interfaces = clazz.getInterfaces();
        return Proxy.newProxyInstance(classLoader,interfaces,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        System.out.println("代理之前");
        method.invoke(target,objects);
        System.out.println("代理之后");
        return null;
    }
}
