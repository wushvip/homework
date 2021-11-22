package com.chinamobile.cmss.oms.springcloud.consume.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangyao
 * @Title
 * @create 2020-09-21 19:12
 * @Description consumer application
 * @since 1.0.0
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@RefreshScope
public class ConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeApplication.class, args);
    }

}
