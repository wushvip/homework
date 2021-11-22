package com.chinamobile.cmss.bdoc.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-11-19 15:09
 * @Description
 * @Since V1.1.0
 */
public class UserRegisterEvent extends ApplicationEvent {
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
