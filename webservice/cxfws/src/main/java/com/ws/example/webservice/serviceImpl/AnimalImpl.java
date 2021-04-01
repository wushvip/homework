package com.ws.example.webservice.serviceImpl;

import com.ws.example.webservice.Animal;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @Author: wushuai
 * @Date: 2020/4/21 19:28
 * @Description:
 */

//@WebService(endpointInterface = "src.main.com.ws.example.service.Animal",serviceName ="AnimalImplService")
//@WebService(endpointInterface = "com.ws.example.service.Animal",targetNamespace = "http://ws.Animal.com")
@WebService(targetNamespace = "/cxf/webservice/AnimalManage")

//@WebService
@Component
public class AnimalImpl implements Animal {

    public String say(String name) {
        return "name" + "\bHI";
    }

    public void run(String  arg) {
        System.out.println("arg:/b" + arg);
    }
}
