package com.chinamobile.cmss.bdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-11-18 18:29
 * @Description
 * @Since V1.1.0
 */
@RestController
@RequestMapping("test")
@RefreshScope
public class TestConfigServer {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${tracer.property}")
    private String property;
//    @Autowired
//    private Envi

    @RequestMapping("")
    public String getProperty(){
        return username + "\nsay :" + property;
    }
}
