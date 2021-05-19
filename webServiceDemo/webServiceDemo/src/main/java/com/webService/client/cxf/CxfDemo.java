package com.webService.client.cxf;

import com.util.CallWebServiceUtil;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * @Author: wushuai
 * @Date: 2020/4/27 16:42
 * @Description:
 */
public class CxfDemo {


    public static void main(String[] args) throws RemoteException, ServiceException, MalformedURLException {
//        String endpoint = "http://localhost:8100/cxf/webservice/PersonManage?wsdl";
        String endpoint = "http://localhost:8080/cxf/webservice/PersonManage?wsdl";
        String targetNameSpace="http://ws.person.com";
        String method = "Say";
//        String endpoint = "http://10.139.19.188:8100/bdoc/webservice/UpdateAppAcctSoap?wsdl";
//        String endpoint = "http://10.139.19.188:7080/cxf/webservice/CheckAiuapTokenSoap?wsdl";
//        String targetNameSpace="http://ws.CheckAiuapTokenSoap.com";
//        String method = "UpdateAppAcctSoap";
        String requestParam = "p";

        CallWebServiceUtil.callWebService(endpoint,targetNameSpace,method,requestParam,"123");
    }



}
