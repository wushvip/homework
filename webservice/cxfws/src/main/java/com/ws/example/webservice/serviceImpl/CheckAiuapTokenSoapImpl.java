package com.ws.example.webservice.serviceImpl;

import com.ws.example.webservice.CheckAiuapTokenSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @autor ws
 * @description
 * @date 2020/6/9 20:02
 **/
@WebService(endpointInterface = "com.ws.example.webservice.CheckAiuapTokenSoap",targetNamespace = "http://ws.CheckAiuapTokenSoap.com")
//@WebService(endpointInterface = "com.ws.example.webservice.CheckAiuapTokenSoap")
public class CheckAiuapTokenSoapImpl implements CheckAiuapTokenSoap {


    public String CheckAiuapTokenSoap(String requestInfo) {
        return "sucess!";
    }
}
