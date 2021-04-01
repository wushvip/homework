package com.rl;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService
@BindingType(value = "http://www.w3.org/2003/05/soap/bindings/HTTP/")
public class PersonService {
	
	public String get(String name) {
		return "love" + name;
	}

}
