package com.chinamobile.cmss.oms.springcloud.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

/*
 * @EnableZipkinServer、@EnableZipkinStreamServer两者二选一
 * 通过源码可看到，@EnableZipkinStreamServer包含了@EnableZipkinServer，同时
 * 还创建了一个rabbit-mq的消息队列监听器，所以也支持原来的HTTP通信方式
 */
@EnableZipkinServer//默认采用HTTP通信方式启动ZipkinServer
//@EnableZipkinStreamServer//采用Stream通信方式启动ZipkinServer，也支持HTTP通信方式
@SpringBootApplication
@RefreshScope
@EnableEurekaClient
public class ZipkinServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}
}
