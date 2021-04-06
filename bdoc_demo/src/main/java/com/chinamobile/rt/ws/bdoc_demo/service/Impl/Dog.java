package com.chinamobile.rt.ws.bdoc_demo.service.Impl;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:28
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.AbstractFood;
import com.chinamobile.rt.ws.bdoc_demo.bean.DogFood;
import com.chinamobile.rt.ws.bdoc_demo.service.Animal;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:28
 * @Description
 * @Since V1.0
 */
@Service
public class Dog implements Animal {
    @Override
    public AbstractFood eat() {
        return new DogFood("gouliang",15.8);
    }
}
