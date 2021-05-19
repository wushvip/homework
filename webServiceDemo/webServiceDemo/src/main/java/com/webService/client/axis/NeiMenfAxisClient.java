//package com.webService.client.axis;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.rmi.RemoteException;
//
//import javax.xml.namespace.QName;
//import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.encoding.XMLType;
//
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//
//
//import com.zero.utils.IOUtils;
//
//public class NeiMenfAxisClient {
//	public static void main(String[] args) throws IOException, ServiceException {
//		/*String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
//		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
//		UpdateAppAcctSoap(endpoint,targetNameSpace);*/
//		try {
//			getUserAmount();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//
//
//	//从账号变更接口
//	private static void UpdateAppAcctSoap(String endpoint,String targetNamespace) throws IOException, ServiceException{
//		String type="query";
//		String method="";
//
//		String filePath =  System.getProperty("user.dir");
//		String path="";
//		String parameterName="";
//		switch(type.toUpperCase()){
//		case "ADD":
//			path="/data/nm2019/user/nm_addUser.xml";
//			break;
//		case "MODIFY":
//			path="/data/nm2019/user/nm_modifyUser.xml";
//			break;
//		case "DEL":
//			method="delUser";
//			parameterName="userIDs";
//			path="/data/nm2019/user/nm_delUser.xml";
//			break;
//		case "query":
//			method="findUser";
//			parameterName="userID";
//			//path="/data/nm2019/user/nm_query";
//			break;
//		default:
//			break;
//		}
//		String value="";
//		//System.out.println("value:"+value);
//		if(!type.equals("query")){
//			File file=new File(filePath,path);
//			value=IOUtils.ReadFromLocal(file);
//		}else{
//			value="testUser0612";
//		}
//		callWebService(endpoint,targetNamespace, method, parameterName,value);
//	}
//
//
//	public static void callWebService(String endpoint,String targetNameSpace,String method,String requestParamName,String value) throws ServiceException, MalformedURLException, RemoteException  {
//		Service service=new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(new URL(endpoint));
//		call.setOperationName(new QName(targetNameSpace,method));
//		call.addParameter(requestParamName, XMLType.XSD_STRING, ParameterMode.IN);
//		call.setReturnClass(String.class);
//		String result =(String)call.invoke(new Object[]{value});
//		System.out.println(result);
//	}
//
//
//	public static void findUser() throws Exception{
//		String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
//		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
//		String method="findUser";
//		String parameterName="userID";
//		String value="admin";
//		Service service=new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(new URL(endpoint));
//		call.setOperationName(new QName(targetNameSpace,method));
//		call.addParameter(parameterName, XMLType.XSD_STRING, ParameterMode.IN);
//		call.setReturnClass(String.class);
//		String result =(String)call.invoke(new Object[]{value});
//		System.out.println(result);
//	}
//
//	public static void findAllUsers() throws Exception{
//		String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
//		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
//		String method="queryUsers";
//		Service service=new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(new URL(endpoint));
//		call.setOperationName(new QName(targetNameSpace,method));
//		call.setReturnClass(String.class);
//		String result =(String)call.invoke(new Object[]{});
//		System.out.println(result);
//	}
//
//	public static void getUserAmount() throws Exception{
//		String endpoint = "http://10.254.4.29:7081/bdoc/nm/updateUser?wsdl";
//		String targetNameSpace="http://webservice.neimeng.bdoc.cmss.chinamobile.com/";
//		String method="getUserAmount";
//		Service service=new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(new URL(endpoint));
//		call.setOperationName(new QName(targetNameSpace,method));
//		call.setReturnClass(String.class);
//		String result =(String)call.invoke(new Object[]{});
//		System.out.println(result);
//	}
//}
