package com.cxf.inter.web.stup;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-05-08T15:11:56.345+08:00
 * Generated source version: 3.2.4
 *
 */
@WebService(targetNamespace = "http://server1.rl.com/", name = "UserService")
@XmlSeeAlso({ObjectFactory.class})
public interface UserService {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://server1.rl.com/", className = "com.cxf.inter.web.stup.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://server1.rl.com/", className = "com.cxf.inter.web.stup.SayHelloResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
