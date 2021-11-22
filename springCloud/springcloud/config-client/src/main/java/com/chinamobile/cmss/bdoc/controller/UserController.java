package com.chinamobile.cmss.bdoc.controller;

import com.chinamobile.cmss.bdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Author wushuai
 * @Date 2021-11-19 15:03
 * @Description
 * @Since V1.1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(String name){
        return userService.register(name);
    }

}
