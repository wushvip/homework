package com.chinamobile.rt.ws.bdoc_demo;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 17:41
 * @Description
 * @Since V1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 17:41
 * @Description
 * @Since V1.0
 */
@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("bdocDemo")
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.chinamobile.rt.ws.bdoc_demo.resource"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger2 test!")
                .description("包括用户、角色、权限等接口")
                .termsOfServiceUrl("http://localhost:8080/v2/api-docs")
                .contact(new Contact("wushuai", "", "sz_ws@139.com"))
                .version("1.0")
                .build();
    }
}
