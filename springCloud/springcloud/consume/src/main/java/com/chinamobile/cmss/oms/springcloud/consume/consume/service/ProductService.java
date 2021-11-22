package com.chinamobile.cmss.oms.springcloud.consume.consume.service;


import com.chinamobile.cmss.oms.springcloud.commonbean.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author wangyao
 * @Title
 * @create 2020-09-21 19:12
 * @Description test service
 * @since 1.0.0
 */

//name 为product项目中application.yml配置文件中的application.name;
//path 为product项目中application.yml配置文件中的context.path;
@FeignClient(name = "product-server",path ="" )
@Component
public interface ProductService {
    @RequestMapping(value = "getProduct")
    Product getProduct();
}
