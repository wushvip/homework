package com.rl.wsService;

import java.util.List;

import javax.jws.WebService;

import com.rl.model.Person;

@WebService
public interface WsPersonService {
	
	String addPerson(Person p,String password);
	
	List<Person> list();
}
