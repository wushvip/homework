/**
 * 
 */
package com.ws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.ownInterface.OperationLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wushuai
 * @date 2018年9月17日
 * @Description	TODO
 */

@RestController
@RequestMapping(value="/hello")
public class HelloWorld {
	@OperationLogger(getAge = 20, getName = "helloworld")
	@RequestMapping(value="/world")
	public String getWord(HttpServletRequest request) {
		System.out.println(this);
		System.out.println(request.getContextPath());
		System.out.println(request.getCookies());
		System.out.println(request.getRequestURL());
		System.out.println(request.getServletPath());
		System.out.println("controller方法执行");
		return "HELLO world!";
	}
	
	
}
