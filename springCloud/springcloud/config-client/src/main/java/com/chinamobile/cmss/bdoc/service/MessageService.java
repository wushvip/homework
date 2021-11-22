package com.chinamobile.cmss.bdoc.service;

import com.chinamobile.cmss.bdoc.event.UserRegisterEvent;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-11-19 14:58
 * @Description
 * @Since V1.1.0
 */
public interface MessageService {

    public void sendMessage(UserRegisterEvent event);
}
