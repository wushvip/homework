package com.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;


public class Publish {

	public static void main(String[] args) {
		publishWithJaxWsServerFactoryBean();
		System.out.println("pulish success!");
		
	}
	/**
	 * 基于接口发布
	 */
	public static void publishWithJaxWsServerFactoryBean() {
		JaxWsServerFactoryBean fb = new JaxWsServerFactoryBean();
		String url = "localhost:8080/user";
		fb.setAddress(url);	
		fb.setServiceClass(UserService.class);
		fb.setServiceBean(new UserServiceImp());
		fb.create();
	}
	
	public static void publishWithJaxWsServerFactoryBeanUserImp() {
		JaxWsServerFactoryBean fb = new JaxWsServerFactoryBean();
		String url = "localhost:8888/user";
		fb.setAddress(url);
		fb.setServiceClass(UserService.class);
		fb.setServiceBean(new UserServiceImp());
		fb.create();
	}
}
