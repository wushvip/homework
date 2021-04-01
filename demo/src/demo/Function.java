	package demo;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName="RLFunction",portName="RLFunctionPort")
public class Function {
	
	 public String transWords(String words){
		 String res = "";
		 	for(char ch : words.toCharArray()){
		 		res += ch+",";
		 	}
		 return res;
	 }
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/demo/function", new Function());
				
		System.out.println("pulish success!");
	}

}
