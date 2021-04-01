package com.rl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rl.model.Person;
import com.rl.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/add")
	public String addPerson() {
		Person p = new Person();
		p.setId(1l);
		p.setGender(1);
		p.setName("÷Ó∏¡¡");
		return personService.addPerson(p);
	}
}
