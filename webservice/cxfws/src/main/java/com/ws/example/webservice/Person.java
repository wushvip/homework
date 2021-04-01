package com.ws.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @Author: wushuai
 * @Date: 2020/4/24 17:51
 * @Description:
 */
@WebService(targetNamespace = "http://ws.person.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Person {

    @WebMethod(operationName = "Say")
    public String say(@WebParam(name = "p") String p);

}
