package com.chinamobile.cmss.oms.springcloud.consume.consume.controller;

import com.chinamobile.cmss.oms.springcloud.commonbean.entity.Product;
import com.chinamobile.cmss.oms.springcloud.consume.consume.service.ProductService;
import com.netflix.discovery.DiscoveryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangyao
 * @Title
 * @create 2020-09-21 19:12
 * @Description test consumer
 * @since 1.0.0
 */

@RestController
@EnableAutoConfiguration
@Slf4j
public class ConsumerController {

    private static String TAG = "ConsumerController";

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "getConsumer")
    public String getConsumer(){
        Product product =  productService.getProduct();
        log.debug("{} receive product content {}", TAG, product.toString());
        return product.getName();
    }

    @RequestMapping(value = "shutdown")
    public void shutdown(){
        DiscoveryManager.getInstance().shutdownComponent();
    }
}
