package com.chinamobile.cmss.bdoc.service.impl;

import com.chinamobile.cmss.bdoc.event.UserRegisterEvent;
import com.chinamobile.cmss.bdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-11-19 14:56
 * @Description
 * 采用spring事件驱动机制：
 * ApplicationEventPublisher(发布者)
 * ApplicationEvent（应用需要发布的事件都需要继承该类）
 * ApplicationListener（监听器，通过发布者的命令执行事件的过程）
 *
 *
 * 使用的步骤：
 * 1、创建一个应用事件
 * 2、注册监听器listener
 *
 * 两种方式：
 * 第一种： 相应的service方法中实现ApplicationListener<UserRegisterEvent>接口
 * 第二种： 通过注解@EventListener
 *
 * 3、在userService方法中注入ApplicationEventPublisher对象即可
 * 注入的方式有两种：
 *  第一种：实现ApplicationEventPublisherAware接口
 *      public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
 *         this.eventPublisher = applicationEventPublisher;
 *     }
 *  第二种： 通过注解的方式注入
 *      @Autowired
 *     private ApplicationEventPublisher eventPublisher;
 *
 * @Since V1.1.0
 */
@Service
public class UserServiceImpl implements UserService, ApplicationEventPublisherAware {

//    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public String register(String name) {
        System.out.println("name register success!");
        eventPublisher.publishEvent(new UserRegisterEvent(name));
        return name + " register success!";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
