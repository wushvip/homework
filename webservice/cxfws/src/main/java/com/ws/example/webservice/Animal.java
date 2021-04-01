package com.ws.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @Author: wushuai
 * @Date: 2020/4/21 19:28
 * @Description:
 */
//targetNamespace = "http://ws.animal.com"
@WebService(serviceName = "AnimalManageService",name = "AnimalManageService",
        targetNamespace = "/cxf/webservice/AnimalManage")
//@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Animal {

    @WebMethod
    String say(@WebParam(name = "name") String name);


    @WebMethod
    void run(@WebParam(name = "arg") String arg);
}
