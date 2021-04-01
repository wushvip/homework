package com.rl.dao;

import java.util.List;

import com.rl.model.Person;

public interface PersonDao {

	void savePerson(Person p);
	
	List<Person> list();
	
}
