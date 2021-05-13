package com.chinamobile.rt.ws.bdoc_demo.service.Component;

import com.chinamobile.rt.ws.bdoc_demo.service.LifeCycle;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-13 10:37
 * @Description
 * @Since V1.0
 */
public interface Component extends LifeCycle {

    public String getComConfigs(String component);
}
