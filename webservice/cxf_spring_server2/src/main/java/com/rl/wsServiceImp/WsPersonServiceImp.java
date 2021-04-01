package com.rl.wsServiceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rl.dao.PersonDao;
import com.rl.model.Person;
import com.rl.wsService.WsPersonService;

@Service
public class WsPersonServiceImp implements WsPersonService{

	@Resource
	private PersonDao personDao;

	public String addPerson(Person p, String password) {
		if("123".equals(password)) {
			personDao.savePerson(p);
			return "success";
		}else {
			return "fail";
		}
	}

	public List<Person> list() {
		return personDao.list();
	}
	
}
