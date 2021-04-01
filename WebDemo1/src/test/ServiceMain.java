package test;

import javax.xml.ws.Endpoint;

public class ServiceMain {

	public static void main(String[] args) {
//		Endpoint.publish("http://localhost:8080/test", new WebServiceImp());
		WebServiceImp webServiceImp = new WebServiceImp();
		String url = "http://localhost:8080/test";
//		Jaxsa
		System.out.println("发布成功！");
	}

}
