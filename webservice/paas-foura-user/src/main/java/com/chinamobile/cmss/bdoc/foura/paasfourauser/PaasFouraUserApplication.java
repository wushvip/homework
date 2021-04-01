package com.chinamobile.cmss.bdoc.foura.paasfourauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class PaasFouraUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaasFouraUserApplication.class, args);
	}

}
