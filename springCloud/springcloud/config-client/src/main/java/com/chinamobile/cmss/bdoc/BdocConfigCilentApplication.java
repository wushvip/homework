package com.chinamobile.cmss.bdoc;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
//@NacosConfigurationProperties(dataId = "pcpaas.demo")
public class BdocConfigCilentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdocConfigCilentApplication.class, args);
	}

}
