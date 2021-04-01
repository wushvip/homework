package com.ws.example.bean;

/**
 * @Author: wushuai
 * @Date: 2020/4/28 10:35
 * @Description:
 */
public class ResponseImpl extends Response {

    private final int code;

    private final Object entity;


    protected ResponseImpl(int code,Object entity){
        this.code  = code;
        this.entity = entity;
    }

}
