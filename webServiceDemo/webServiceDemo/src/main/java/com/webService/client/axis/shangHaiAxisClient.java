/*
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

public class shangHaiAxisClient {
	public static void main(String[] args) throws IOException, ServiceException {
//		String endpoint = "http://10.254.4.29:7081/bdoc/sh/updateUser?wsdl";
		String endpoint = "http://10.139.19.188:8100/bdoc/sh/facadeService?wsdl";
//		String targetNameSpace="http://facade.service.fourA.shanghai.bdoc.cmss.chinamobile.com/";
		String targetNameSpace="http://facade.service.fourA.shanghai.bdoc.cmss.chinamobile.com/";
		int type = 1;
		UpdateAppAcctSoap(endpoint,targetNameSpace,type);
		//UpdateAppAuthorServices(endpoint,targetNameSpace);
		//UpdateAppAcctAuthorServices(endpoint,targetNameSpace);
//		updateAppAuthorRelServices(endpoint,targetNameSpace);

	}

	*/
/**
	 * role_permission接口
	 * @param endpoint
	 * @param targetNamespace
	 * @throws IOException
	 * @throws ServiceException
	 *//*

//	private static void updateAppAuthorRelServices(String endpoint,String targetNamespace) throws IOException, ServiceException{
//		String type="DELETE";
//		String method="UpdateAppAuthorRelServices";
//		String filePath =System.getProperty("user.dir");
//		String path="";
//		String parameterName="RequestInfo";
//		switch(type.toUpperCase()){
//		case "ALL":
//			path="/data/shanghai/role_permission/shanghai_allRolePermission.xml";
//			break;
//		case "ADD":
//			path="/data/shanghai/role_permission/shanghai_addRolePermission.xml";
//			break;
//		case "UPDATE":
//			path="/data/shanghai/role_permission/shanghai_updateRolePermission.xml";
//			break;
//		case "DELETE":
//			path="/data/shanghai/role_permission/shanghai_deleteRolePermission.xml";
//			break;
//		default:
//			break;
//		}
//		File file=new File(filePath,path);
//		String value=IOUtils.ReadFromLocal(file);
//		//System.out.println("value:"+value);
//		callWebService(endpoint,targetNamespace, method, parameterName,value);
//
//	}

	*/
/**
	 * user_role变更接口
	 * @param endpoint
	 * @param targetNamespace
	 * @throws IOException
	 * @throws ServiceException
	 *//*

//	private static void UpdateAppAcctAuthorServices(String endpoint,String targetNamespace) throws IOException, ServiceException{
//		String type="DELETE";
//		String method="UpdateAppAcctAuthorServices";
//		String filePath = System.getProperty("user.dir");
//		String path="";
//		String parameterName="RequestInfo";
//		switch(type.toUpperCase()){
//		case "ALL":
//			path="/data/shanghai/user_role/shanghai_allUserRole.xml";
//			break;
//		case "ADD":
//			path="/data/shanghai/user_role/shanghai_addUserRole.xml";
//			break;
//		case "UPDATE":
//			path="/data/shanghai/user_role/shanghai_updateUserRole.xml";
//			break;
//		case "DELETE":
//			path="/data/shanghai/user_role/shanghai_deleteUserRole.xml";
//			break;
//		default:
//			break;
//		}
//		File file=new File(filePath,path);
//		String value=IOUtils.ReadFromLocal(file);
//		//System.out.println("value:"+value);
//		callWebService(endpoint,targetNamespace, method, parameterName,value);
//
//	}

	//从账号变更接口
	private static void UpdateAppAcctSoap(String endpoint,String targetNamespace,int type) throws IOException, ServiceException{
//		String type="modify";
		String method="UpdateAppAcctSoap";
		String filePath =  System.getProperty("user.dir");
		String path="";
		String parameterName="RequestInfo";
//        String upperType = type.toUpperCase();
        switch(type){
		case 1:
			path="/data/shanghai/user/shanghai_addUser.xml";
			break;
		case 2:
			path="/data/shanghai/user/shanghai_modifyUser.xml";
			break;
		case 3:
			path="/data/shanghai/user/shanghai_changeStatus.xml";
			break;
		case 4:
			path="/data/shanghai/user/shanghai_resetPwd.xml";
		case 5:
			path="/data/shanghai/user/shanghai_delete.xml";
		default:
			break;
		}
		File file=new File(filePath,path);
		String value=IOUtils.ReadFromLocal(file);
		//System.out.println("value:"+value);
		callWebService(endpoint,targetNamespace, method, parameterName,value);
	}

	*/
/**
	 * 角色变更接口
	 * @param endpoint
	 * @param targetNamespace
	 * @throws IOException
	 * @throws ServiceException
	 *//*

//	public static void UpdateAppAuthorServices(String endpoint,String targetNamespace) throws IOException, ServiceException{
//		//add update delete
//		String type="delete";
//		String method="UpdateAppAuthorServices";
//		String filePath =  System.getProperty("user.dir");
//		String path="";
//		String parameterName="RequestInfo";
//		switch(type.toUpperCase()){
//		case "ADD":
//			path="/data/shanghai/role/shanghai_addRole.xml";
//			break;
//		case "UPDATE":
//			path="/data/shanghai/role/shanghai_updateRole.xml";
//			break;
//		case "DELETE":
//			path="/data/shanghai/role/shanghai_deleteRole.xml";
//			break;
//		default:
//			break;
//		}
//		File file=new File(filePath,path);
//		String value=IOUtils.ReadFromLocal(file);
//		//System.out.println("value:"+value);
//		callWebService(endpoint,targetNamespace, method, parameterName,value);
//	}



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
}
*/
