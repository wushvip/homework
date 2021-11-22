package com.chinamobile.cmss.oms.springcloud.product.product.controller;


import com.chinamobile.cmss.oms.springcloud.commonbean.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangyao
 * @Title ProductController
 * @create 2020-09-21 19:12
 * @Description test product controller
 * @RefreshScope 必须添加此属性才能实时刷新配置
 * @since 1.0.0
 */

@RestController
@EnableAutoConfiguration
@Slf4j
@RefreshScope
public class ProductController {

    @Value("${zone.name}")
    private String zoneName;

    private static String TAG = "ProductController";

    @RequestMapping(value = "getProduct")
    public Product getProduct() {
        log.info("ProductController-zone-name [{}]", zoneName);
        Product product = new Product();
        product.setName(zoneName);
        return product;
    }
}
