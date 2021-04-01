package com.ws.example.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @Author: wushuai
 * @Date: 2020/4/21 19:28
 * @Description:
 */
@WebService(serviceName = "AnimalManage",name = "AnimalManage",portName = "AnimalManageImplPort",targetNamespace = "http://ws.tyqk.karaka2.com.cn")
public interface Animal {

    @WebMethod
    @SOAPBinding(style = SOAPBinding.Style.RPC)
    String say(String name);
}
