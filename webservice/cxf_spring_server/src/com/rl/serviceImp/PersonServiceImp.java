package com.rl.serviceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rl.dao.PersonDao;
import com.rl.model.Person;
import com.rl.service.PersonService;

@Service
public class PersonServiceImp implements PersonService{
	
	@Resource
	private PersonDao personDao;
	
	@Override
	public String addPerson(Person p) {
		 personDao.savePerson(p);
		 return "succ";
		
	}

	@Override
	public List<Person> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
