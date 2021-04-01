package com.rl.server1;

import javax.jws.WebService;

@WebService
public interface UserService {
	
	String sayHello(String name);
}
