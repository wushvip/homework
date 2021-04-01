package com.chinamobile.cmss.bdoc.foura.paasfourauser.webservice;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

@WebService
@Service
public class HelloWorldImpl implements HelloWorld {
    @Override
    public void testWS() {
        System.out.println("-------------");
    }
}
