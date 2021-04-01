package com.exmple.serverlt.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @autor ws
 * @description
 * @date 2021/3/17 20:42
 **/
@WebListener
public class SSOListerner implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("SSOListerner context  Initialized");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SSOListerner context  Destroyed");
    }
}
