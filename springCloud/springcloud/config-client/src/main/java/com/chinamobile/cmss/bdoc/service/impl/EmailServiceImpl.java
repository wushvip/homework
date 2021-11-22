package com.chinamobile.cmss.bdoc.service.impl;

import com.chinamobile.cmss.bdoc.event.UserRegisterEvent;
import com.chinamobile.cmss.bdoc.service.EmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-11-19 15:00
 * @Description
 * @Since V1.1.0
 */
@Service
public class EmailServiceImpl implements EmailService, ApplicationListener<UserRegisterEvent> {
    @Override
//    @EventListener
    public void sendEmail(UserRegisterEvent event) {
        System.out.println("send email to: " + event.getSource());
    }

    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("send email to: " + userRegisterEvent.getSource());
    }
}
