package com.chinamobile.cmss.oms.springcloud.product.product.controller;

import com.chinamobile.cmss.oms.springcloud.product.product.service.HystrixProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
@RestController
public class HystrixProductController {

    @Autowired
    HystrixProductService hystrixProductService;

    @RequestMapping(value = "/product/hystrix", method = RequestMethod.GET)
    public String productHystrix(@RequestParam String name) {
        return hystrixProductService.productHystrix(name);
    }
}
