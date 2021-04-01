package com.ws.example.controller;

import com.ws.example.bean.Response;
import com.ws.example.service.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: wushuai
 * @Date: 2020/4/22 10:51
 * @Description:
 */


@Controller
@RequestMapping("/test")
public class TestController {


    @Resource(name = "testImpl")
//    @Autowired
    private TestInterface testInterface;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String test(String name){
        String test = testInterface.test();
        return "name : " + name + test;
    }
}
