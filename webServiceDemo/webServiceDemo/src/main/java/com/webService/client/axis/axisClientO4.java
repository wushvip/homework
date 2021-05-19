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

public class axisClientO4 {
	public static void main(String[] args) throws IOException {
//		String endpoint = "http://10.254.4.29:7081/bdoc/oss4/updateUser?wsdl";
		String endpoint = "http://10.139.15.120:8100/bdoc/gz/updateUser?wsdl";
		addUser(endpoint);
//		delUser(endpoint);
		//modifyUser(endpoint);
		
		
	}
	
	private static void modifyUser(String endpoint) throws IOException, MalformedURLException, RemoteException {
		String method="modifyUserInfo";
		String filePath =  System.getProperty("user.dir");
		String path="/data/oss4/oss4_modifyUser.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		System.out.println("request"+value);
		try {
			
			callWebService(endpoint, method, "userInfos",value);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void delUser(String endpoint) throws IOException, MalformedURLException, RemoteException {
		String method="delUser";
		String filePath =  System.getProperty("user.dir");
		String path="/data/oss4/oss4_delUser.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		System.out.println("request"+value);
		try {
			
			callWebService(endpoint, method, "userIDs",value);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static void addUser(String endpoint) throws IOException, MalformedURLException, RemoteException {
		String method="addUserInfo";
		String filePath =  System.getProperty("user.dir");
		String path="/data/oss4/oss4_addUser.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		System.out.println("request"+value);
		try {
			
			callWebService(endpoint, method, "userInfos",value);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void callWebService(String endpoint,String method,String requestParamName,String value) throws ServiceException, MalformedURLException, RemoteException  {
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		String targetNameSpace="http://webservice.o4.bdoc.cmss.chinamobile.com/";
		call.setOperationName(new QName(targetNameSpace,method));
		call.addParameter(requestParamName, XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value});
		System.out.println(result);
	}
}
