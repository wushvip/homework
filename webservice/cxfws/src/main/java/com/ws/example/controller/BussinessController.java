package com.ws.example.controller;

import com.ws.example.bean.BussinessBean;

import com.ws.example.bean.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: wushuai
 * @Date: 2020/4/28 9:04
 * @Description:
 */
@RestController
@RequestMapping(value = "/bussiness")
public class BussinessController {


    @RequestMapping(value = "/create")
    public Response create(Map<String,Object> map){

        BussinessBean bb = new BussinessBean();
        Response.ResponseBuilder builder = Response.init(bb);

        return builder.build();

    }
}
