package com.client;

import java.util.List;

import com.rl.web.stub.Person;
import com.rl.web.stub.WsPersonService;
import com.rl.web.stub.WsPersonServiceService;

public class Client_test01 {

	public static void main(String[] args) {
		Client_test01.addPerson();
//		list();
	}
	public static void addPerson() {
		WsPersonServiceService wss = new WsPersonServiceService();
		WsPersonService ws = wss.getWsPersonServicePort();
		Person p = new Person();
		p.setId(1l);
		p.setName("уе╥и");
		p.setGender(1);
		String result = ws.addPerson(p, "123");
		System.out.println(result);
	}
	
	
	public static void list() {
		WsPersonServiceService wss = new WsPersonServiceService();
		WsPersonService ws = wss.getWsPersonServicePort();
		List<Person> plists = ws.list();
		
		if(plists !=null && plists.size()>0) {
			for(Person p:plists) {
				System.out.println("id: " + p.getId() +"name: " + p.getName() + "gender: " + p.getGender());
			}
		}
	}
}
