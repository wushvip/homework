package com.webService.client.axis;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;


import com.zero.utils.IOUtils;

public class axisClient3 {
	public static void main(String[] args) throws IOException, ServiceException {
		String endpoint = "http://127.0.0.1:8080/services/updateUser?wsdl";
		String method="TestTwo";
		String value1="hello";
		String value2="suzhou";
		callWebService(endpoint, method, value1,value2);
		
	}
	
	
	
	public static void callWebService(String endpoint,String method,String value,String value2) throws ServiceException, MalformedURLException, RemoteException  {
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		//call.setOperationName(new QName("Http://aaaa/test",method));
		call.setOperationName(new QName("UpdateUserService",method));
		//call.setOperation(method);
		call.addParameter("Request1", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("Request2", XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value,value2});
		System.out.println(result);
	}
}
