package com.ws.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://ws.CheckAiuapTokenSoap.com")
//@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CheckAiuapTokenSoap {

    @WebMethod(operationName = "CheckAiuapTokenSoap")
    public String CheckAiuapTokenSoap(@WebParam(name = "RequestInfo") String requestInfo);
}
