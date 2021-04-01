package com.chinamobile.cmss.bdoc.foura.paasfourauser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
