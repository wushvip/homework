package com.webService.client.axis;

import com.zero.utils.IOUtils;
import org.apache.axis.*;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.message.*;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.Messages;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingProvider;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

/**
 * @Author: wushuai
 * @Date: 2020/4/27 15:18
 * @Description:
 */
public class ShClient {



    public static void main(String[] args) throws IOException, ServiceException {
        String endpoint = "http://10.139.19.188:8100/bdoc/webservice/UpdateAppAcctSoap?wsdl";
//        String endpoint = "http://localhost:8080/bdoc/webservice/UpdateAppAcctSoap?wsdl";
//        String targetNameSpace="http://facade.webservice.bdoc.com";
        String targetNameSpace="UpdateAppAcctSoap";
        int type = 1;
        UpdateAppAcctSoap(endpoint,targetNameSpace,type);
    }


    private static void UpdateAppAcctSoap(String endpoint,String targetNamespace,int type) throws IOException, ServiceException {
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
        String value= IOUtils.ReadFromLocal(file);
        //System.out.println("value:"+value);
        callWebService2(endpoint,targetNamespace, method, parameterName,value);
//        try {
//            callWebService(endpoint,targetNamespace, method, parameterName,value);
//        } catch (SOAPException e) {
//            e.printStackTrace();
//        }
    }


    public static void callWebService(String endpoint,String targetNameSpace,String method,String requestParamName,String value) throws ServiceException, MalformedURLException, RemoteException, SOAPException, UnsupportedEncodingException {
        Service service=new Service();
        Call call = (Call) service.createCall();

        
        call.setTargetEndpointAddress(new URL(endpoint));
        call.setOperationName(new QName(targetNameSpace,method));
        call.addParameter(requestParamName, XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnClass(String.class);


        String result =(String)call.invoke(new Object[]{value});
        System.out.println(result);





    }


    public static void callWebService2(String endpoint,String targetNameSpace,String method,String requestParamName,String value){
         String respStr;
        Service service=new Service();
        Call call;
        String clientIp = "192.168.5.88";
        try {
            call = (Call) service.createCall();
//       // 产生 SOAP Header
//       SOAPHeaderElement cpHeader = new SOAPHeaderElement(
//             fourAServiceName, fourAServiceName);
//       cpHeader.setPrefix("");
//       cpHeader.setMustUnderstand(true);
//       SOAPElement ele = cpHeader.addChildElement(PropertyUtil.get(FourAConstants.BDOC_4A_HEADER_NAME));
//       ele.addTextNode(clientIp==null?PropertyUtil.get(FourAConstants.BDOC_CLIENT_IP):clientIp);
//       // 设定 Header
//       call.addHeader(cpHeader);
            call.setTimeout(3000);
            call.setTargetEndpointAddress(new URL(endpoint));
            call.setOperationName(new QName(targetNameSpace, method));
            call.setReturnType(Constants.XSD_STRING);
            call.setEncodingStyle("UTF-8");
            call.addParameter(requestParamName, Constants.XSD_STRING, ParameterMode.IN);

            MimeHeaders headers = new MimeHeaders();
            headers.setHeader("client_ip", clientIp);
            MessageContext messageContext = call.getMessageContext();

            Object respObj = null;
            Object[] params = new Object[] { value };
            RPCElement body = new RPCElement(call.getOperationName().getNamespaceURI(),
                    call.getOperationName().getLocalPart(), getParamList(call,params));

            SOAPEnvelope reqEnv = new SOAPEnvelope(call.getMessageContext().getSOAPConstants(), call.getMessageContext().getSchemaVersion());
            Message reqMsg = new Message(reqEnv, headers);
            Message resMsg = null;
//            this.outParams = new HashMap();
//            this.outParamsList = new ArrayList();

            try {
                body.setEncodingStyle(call.getEncodingStyle());
                call.setRequestMessage(reqMsg);
                reqEnv.addBodyElement(body);
                reqEnv.setMessageType("request");
                call.invoke();
                resMsg = call.getMessageContext().getResponseMessage();
            } catch (Exception var18) {
                throw AxisFault.makeFault(var18);
            }


            
            respObj = ((RPCParam)((RPCElement) resMsg.getSOAPEnvelope().getFirstBody()).getParams().get(0)).getObjectValue();

            respStr = (String) respObj;
            System.out.println(respStr);
//            LOGGER.info("respStr:"+respStr);
        } catch (ServiceException e) {
//            LOGGER.error("service create call failed", e);
            e.printStackTrace();

        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }


    private static Object[] getParamList(Call call, Object[] params) {
        if (call.getOperation() != null && call.getOperation().getNumParams() != 0) {
            int numParams = call.getOperation().getNumInParams();
            if (params != null && numParams == params.length) {
//                LOGGER.info("getParamList number of params: " + params.length);
                Vector result = new Vector();
                int j = 0;
                ArrayList parameters = call.getOperation().getParameters();

                for(int i = 0; i < parameters.size(); ++i) {
                    ParameterDesc param = (ParameterDesc)parameters.get(i);
                    if (param.getMode() != 2) {
                        QName paramQName = param.getQName();
                        RPCParam rpcParam = null;
                        Object p = params[j++];
                        if (p instanceof RPCParam) {
                            rpcParam = (RPCParam)p;
                        } else {
                            rpcParam = new RPCParam(paramQName.getNamespaceURI(), paramQName.getLocalPart(), p);
                        }

                        rpcParam.setParamDesc(param);
                        if (param.isInHeader()) {
                            call.addHeader(new RPCHeaderParam(rpcParam));
                        } else {
                            result.add(rpcParam);
                        }
                    }
                }

                return result.toArray();
            } else {
                throw new JAXRPCException(Messages.getMessage("parmMismatch00", params == null ? "no params" : "" + params.length, "" + numParams));
            }
        } else {
            return params;
        }
    }
}
