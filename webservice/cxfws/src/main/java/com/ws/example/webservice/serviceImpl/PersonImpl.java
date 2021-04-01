package com.ws.example.webservice.serviceImpl;

import com.ws.example.webservice.Person;

import javax.jws.WebService;

/**
 * @Author: wushuai
 * @Date: 2020/4/24 17:55
 * @Description:
 */
@WebService(endpointInterface = "com.ws.example.webservice.Person",targetNamespace = "http://ws.person.com")
public class PersonImpl implements Person {

    public String say(String p) {
        return "p say\n" + p + "\nlangige" ;
    }
}
