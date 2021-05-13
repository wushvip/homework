package com.chinamobile.rt.ws.bdoc_demo.utils;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-21 15:07
 * @Description
 * @Since V1.0
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-21 15:07
 * @Description
 * @Since V1.0
 */
@Component
public final class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static <T> T getBeanByName(String beanName){
        return (T) getApplicationContext().getBean(beanName);
    }

    public static <T> T getBeanByClassType(Class<T> clazz){
        return (T) getApplicationContext().getBean(clazz);
    }
//    private Object getBeanByName(String beanName){
//
//    }
}
