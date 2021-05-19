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

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;


import com.zero.utils.IOUtils;

public class ShanxiClient {
	public static void main(String[] args) throws IOException, ServiceException {
		/*String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
		UpdateAppAcctSoap(endpoint,targetNameSpace);*/
		try {
			//delAccount();
			addAccounts();
			//modifyAccounts();
			//modifyUserRole();
			//modifyAccount();
//			addRole();
			//delRole();
			//modifyRole();
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

	public static void queryAccounts() throws ServiceException, MalformedURLException, RemoteException {
		//客户端调用方式
		//1\获取wsdl相关信息，wsdl以及targetNameSpace、method
		//2、获取方法参数
		//3、生成客户端实例对象，使用反射调用远程方法

		String endpoint = "http://10.139.18.136:7080/bdoc/bdpaas/updateUser?wsdl";
		String targetNamespace="http://webservice.shanxi.bdoc.cmss.chinamobile.com/";
		String method = "UserQuery";

		String param = "lisi";


		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		call.setOperationName(new QName(targetNamespace,method));
		call.setReturnClass(String.class);
		String result = (String) call.invoke(new Object[]{param});

		System.out.println(result);
	}

	
	public static void addAccounts() throws Exception{
//		String endpoint = "http://127.0.0.1:8081/services/user?wsdl";
		String endpoint = "http://10.139.18.136:7080/bdoc/bdpaas/updateUser?wsdl";
//		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String targetNameSpace="http://webservice.shanxi.bdoc.cmss.chinamobile.com/";
		String method="UserAdd";
		String parameterName="request";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/shanxi/shanxi_addUser.xml";
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
	
	public static void delAccount() throws Exception{
		String endpoint = "http://127.0.0.1:8081/services/user?wsdl";
		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String method="UserDelete";
		String parameterName="request";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/shanxi/shanxi_delUser.xml";
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
	
	public static void modifyAccount() throws Exception{
		String endpoint = "http://127.0.0.1:8081/services/user?wsdl";
		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String method="UserModify";
		String parameterName="request";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/shanxi/shanxi_modifyUser.xml";
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
	
	public static void addRole() throws Exception{
//		String endpoint = "http://10.254.4.29:8089/services/user?wsdl";
		String endpoint = "http://10.139.18.136:7080/bdoc/bdpaas/updateUser？wsdl";
		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String method="RoleAdd";
		String parameterName="request";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/shanxi/shanxi_addRole.xml";
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
	
	public static void delRole() throws Exception{
		String endpoint = "http://127.0.0.1:8081/services/user?wsdl";
		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String method="RoleDelete";
		String parameterName="request";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/shanxi/shanxi_delRole.xml";
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
	
	public static void modifyRole() throws Exception{
		String endpoint = "http://127.0.0.1:8081/services/user?wsdl";
		String targetNameSpace="http://webService.paasfourauser.foura.cmss.chinamobile.com/";
		String method="RoleModify";
		String parameterName="request";
		
		String filePath =  System.getProperty("user.dir");
		String path="/data/shanxi/shanxi_modifyRole.xml";
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
