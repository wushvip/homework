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

public class axisClient {
	public static void main(String[] args) throws IOException {
		String endpoint = "http://10.139.16.209:8100/bdoc/bdpaas/updateUser?wsdl";
		String method="UpdateAppAcctSoap";
		String filePath =  System.getProperty("user.dir");
		String path="/data/jituan_addUser.xml";
		//String path="/data/jituan_changestatus.xml";
		//String path="/data/jituan_resetPwd.xml";
		//String path="/data/jituan_updateUser.xml";
		//String path="/data/jituan_delUser.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		System.out.println("request"+value);
		try {
			callWebService(endpoint, method, "RequestInfo",value);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void callWebService(String endpoint,String method,String requestParamName,String value) throws ServiceException, MalformedURLException, RemoteException  {
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		call.setOperationName(new QName(method,method));
		call.addParameter(requestParamName, XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value});
		System.out.println(result);
	}
}
