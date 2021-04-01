package com.ws.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wushuai
 * @Date: 2020/4/27 12:04
 * @Description:
 */
@RestController
@RequestMapping(value = "/jsp")
public class JspController {

    @RequestMapping(method = RequestMethod.GET,value = "/index")
    public void redirectToIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request,response);
    }
}
