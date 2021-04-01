package com.rl.dao;

import java.util.List;

import com.rl.model.Person;

public interface PersonDao {

	abstract void savePerson(Person p);
	
	List<Person> list();
	
}
