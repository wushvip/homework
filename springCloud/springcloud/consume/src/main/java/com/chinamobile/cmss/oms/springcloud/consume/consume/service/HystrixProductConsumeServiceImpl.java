package com.chinamobile.cmss.oms.springcloud.consume.consume.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author LIAOJIANYA
 * @date 2020/9/24
 */
@Service
@Slf4j
public class HystrixProductConsumeServiceImpl implements HystrixProductConsumeService{

    @Autowired
    RestTemplate restTemplate;


    @Override
    @HystrixCommand(fallbackMethod = "productHystrixFallback")
    public String consumeHystrixProduct(String name) {
        log.info("query product-server product/hystrix api... ...");
        //通过使用注册到eureka中的product-server服务名进行负载均衡访问
        return restTemplate.getForObject("http://product-server/product/hystrix?name=" + name, String.class);
    }


    /**
     * 服务降级处理
     * @param name
     * @return
     */
    public String productHystrixFallback(String name) {
        log.error("name={} : product-server api is unable to be accessed!", name);
        log.info("name={} : hystrix-fallback success", name);
        //降级处理返回信息
        return "name=" + name + " : product hystrix error! begin to fallback... ...";
    }
}
