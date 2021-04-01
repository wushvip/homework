package com.rl.server1;

public class UserServiceImp implements UserService {

	@Override
	public String sayHello(String name) {
		return "Hello  " + name;
	}

}
 