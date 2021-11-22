package com.chinamobile.cmss.oms.springcloud.consume.consume.controller;

import com.chinamobile.cmss.oms.springcloud.consume.consume.service.HystrixProductConsumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
@RestController
public class HystrixConsumeController {


    @Autowired
    HystrixProductConsumeServiceImpl hystrixProductConsumeServiceImpl;


    @RequestMapping(value = "/consume/hystrix", method = RequestMethod.GET)
    public String consumeHystrix(String name) {
        return hystrixProductConsumeServiceImpl.consumeHystrixProduct(name);
    }
}
