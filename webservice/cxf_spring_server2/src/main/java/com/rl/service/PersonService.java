package com.rl.service;

import java.util.List;

import com.rl.model.Person;

public interface PersonService {
	
	String addPerson(Person p);
	
	List<Person> list();
}
