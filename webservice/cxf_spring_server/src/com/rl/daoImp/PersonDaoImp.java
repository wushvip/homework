package com.rl.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rl.dao.PersonDao;
import com.rl.model.Person;

@Repository
public class PersonDaoImp implements PersonDao {

	List<Person> plist = new ArrayList<Person>();
	
	@Override
	public void savePerson(Person p) {
		plist.add(p);
	}

	@Override
	public List<Person> list() {
		return plist;
	}

}
