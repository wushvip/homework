package com.chinamobile.rt.ws.bdoc_demo.bean;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 10:26
 * @Description
 * @Since V1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 10:26
 * @Description
 * @Since V1.0
 */
@Configuration
public class FileConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以便在UploadAction 中捕获文件大小异常
        resolver.setResolveLazily(true);
        //每个文件最大上传300MB
        resolver.setMaxUploadSizePerFile(1024 * 1024 * 300);
        //最大文件大小  350M
        resolver.setMaxUploadSize(1024 * 1024 * 350);
        resolver.setMaxInMemorySize(1024);
        return resolver;
    }
}
