package com.cxf;

import javax.jws.WebService;

@WebService
public interface UserService {
	
	public String sayHello(String name);
}
