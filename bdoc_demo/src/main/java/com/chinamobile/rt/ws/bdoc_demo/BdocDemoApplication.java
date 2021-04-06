package com.chinamobile.rt.ws.bdoc_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Springboot关闭数据源的配置，只需增加exclude = {DataSourceAutoConfiguration.class}即可
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
public class BdocDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdocDemoApplication.class, args);
	}

}
