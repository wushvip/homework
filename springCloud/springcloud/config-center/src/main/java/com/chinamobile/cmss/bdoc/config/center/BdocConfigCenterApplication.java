package com.chinamobile.cmss.bdoc.config.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BdocConfigCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdocConfigCenterApplication.class, args);
	}

}
