package com.chinamobile.cmss.bdoc.foura.paasfourauser.config;

import com.chinamobile.cmss.bdoc.foura.paasfourauser.webservice.HelloWorld;
import com.chinamobile.cmss.bdoc.foura.paasfourauser.webservice.HelloWorldImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private HelloWorld helloWorld;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus,helloWorld);
        endpoint.publish("/helloWorld");
        return endpoint;

    }
}
