package com.chinamobile.rt.ws.bdoc_demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-07 20:49
 * @Description
 * @Since V1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataForm {

}
