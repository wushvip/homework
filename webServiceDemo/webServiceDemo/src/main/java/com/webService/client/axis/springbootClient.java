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

public class springbootClient {
	public static void main(String[] args) throws IOException, ServiceException {
		/*String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
		UpdateAppAcctSoap(endpoint,targetNameSpace);*/
		try {
//			addAccounts();
			//modifyAccounts();
			modifyUserRole();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	public static void callWebService(String endpoint,String targetNameSpace,String method,String requestParamName,String value) throws ServiceException, MalformedURLException, RemoteException  {
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		call.setOperationName(new QName(targetNameSpace,method));
		call.addParameter(requestParamName, XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value});
		System.out.println(result);
	}
	
	
	public static void addAccounts() throws Exception{
		String endpoint = "http://127.0.0.1:8081/services/user?wsdl";
		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String method="addAccounts";
		String parameterName="addParam";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/nm2019/xilidu/user/addUser.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		call.setOperationName(new QName(targetNameSpace,method));
		call.addParameter(parameterName, XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value});
		System.out.println(result);
				
	}
	
	public static void modifyAccounts() throws Exception{
		String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
		String method="modifyAccounts";
		String parameterName="modifyParam";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/nm2019/xilidu/user/modifyUser.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		call.setOperationName(new QName(targetNameSpace,method));
		call.addParameter(parameterName, XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value});
		System.out.println(result);
				
	}
	
	public static void modifyUserRole() throws Exception{
		String endpoint = "http://10.254.4.29:7082/bdoc/bdpaas/updateUser?wsdl";
		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
		String method="modifyAccRoles";
		String parameterName="modifyParam";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/nm2019/xilidu/user/user_role.xml";
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		
		Service service=new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		call.setOperationName(new QName(targetNameSpace,method));
		call.addParameter(parameterName, XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(String.class);
		String result =(String)call.invoke(new Object[]{value});
		System.out.println(result);
				
	}
	
}
