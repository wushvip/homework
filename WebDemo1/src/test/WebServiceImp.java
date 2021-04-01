package test;

@javax.jws.WebService(endpointInterface="test.WebService")
public class WebServiceImp implements WebService {

	@Override
	public String sayWord() {
		return "hello";
	}

}
