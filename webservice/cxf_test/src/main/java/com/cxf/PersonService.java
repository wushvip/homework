package com.cxf;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(name="RLPersonService")
public class PersonService {
	
	private List<Person> pList = new ArrayList<Person>();
	
	public void addPerson(Person p) {
		pList.add(p);
	}
	
	public void deletePerson(int id) {
		for(int i=0;i<pList.size();i++) {
			if(id==pList.get(i).getId()) {
				pList.remove(pList.get(i));
			}
		}
	}
	
	public List<Person> list(){
		return pList;
	}
}
