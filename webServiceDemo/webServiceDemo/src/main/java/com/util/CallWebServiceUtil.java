package com.util;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * @Author: wushuai
 * @Date: 2020/4/27 16:45
 * @Description:
 */
public class CallWebServiceUtil {

    public static void callWebService(String endpoint,String targetNameSpace,String method,String requestParamName,String value) throws ServiceException, MalformedURLException, RemoteException {
        Service service=new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(endpoint));
        call.setOperationName(new QName(targetNameSpace,method));
        call.addParameter(requestParamName, XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnClass(String.class);
        String result =(String)call.invoke(new Object[]{value});
        System.out.println(result);
    }
}
