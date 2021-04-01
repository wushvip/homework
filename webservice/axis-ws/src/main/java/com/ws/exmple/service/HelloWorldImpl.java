package com.ws.exmple.service;

import org.springframework.context.ApplicationContext;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import javax.xml.rpc.ServiceException;


public class HelloWorldImpl extends ServletEndpointSupport implements HelloWorld {

    @Override
    protected void onInit() throws ServiceException {
        System.out.println("this is onInit()");
        ApplicationContext applicationContext = getApplicationContext();
        applicationContext.getBean("");

        super.onInit();
    }

    public String askFrom(String name) {

        System.out.println("from: " + name);
        return "ask from :" + name + ", and answer: hi" + name;
    }
}
