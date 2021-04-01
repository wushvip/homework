package com.ws.example.service.serviceImpl;

import com.ws.example.service.Animal;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @Author: wushuai
 * @Date: 2020/4/21 19:28
 * @Description:
 */

//@WebService(endpointInterface = "src.main.com.ws.example.service.Animal",serviceName ="AnimalImplService")
@WebService
@Service
public class AnimalImpl implements Animal {
    public String say(String name) {
        return "name" + "\bHI";
    }
}
