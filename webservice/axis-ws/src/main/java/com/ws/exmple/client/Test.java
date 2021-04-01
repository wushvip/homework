package com.ws.exmple.client;

import org.apache.axis.Constants;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class Test {

    public static void main(String[] args) throws RemoteException, ServiceException, MalformedURLException {
        String url ="http://localhost:8080/axis/services/HelloWorld?wsdl";
        String namespace ="http://localhost:8080/axis/services/HelloWorld?wsdl";
        String operateName = "askFrom";
        String res = callFourAService(url, operateName, "name", "tom", null);
        System.out.println(res);
    }

    public static String callFourAService(String url, String fourAServiceName,
                                          String fourAServiceRequestInfo, String reqValue, String fourAServiceResponseInfo) throws ServiceException, MalformedURLException, RemoteException {
        String respStr = null;
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTimeout(3000);
            call.setTargetEndpointAddress(new URL(url));
            call.setOperationName(new QName(fourAServiceName, fourAServiceName));
            call.addParameter(fourAServiceRequestInfo, Constants.XSD_STRING, ParameterMode.IN);
            call.setReturnType(Constants.XSD_STRING);
            call.setEncodingStyle("UTF-8");
            respStr = (String) call.invoke(new Object[]{reqValue});
        } catch (ServiceException e) {
            throw e;
        } catch (MalformedURLException e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        }
        return respStr;
    }
}
