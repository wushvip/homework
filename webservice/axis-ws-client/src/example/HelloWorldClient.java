package example;


import jws.HelloWorldImpl;
import jws.HelloWorldImplServiceLocator;
import jws.HelloWorldSoapBindingStub;

import javax.xml.rpc.Call;
import java.rmi.activation.Activator;

public class HelloWorldClient {
  public static void main(String[] argv) {
      try {
            HelloWorldImplServiceLocator locator = new HelloWorldImplServiceLocator();
          HelloWorldImpl helloWorld = locator.getHelloWorld();
          helloWorld.askFrom("jom");

          // If authorization is required
          //((HelloWorldSoapBindingStub)service).setUsername("user3");
          //((HelloWorldSoapBindingStub)service).setPassword("pass3");
          // invoke business method

      } catch (javax.xml.rpc.ServiceException ex) {
          ex.printStackTrace();
      } catch (java.rmi.RemoteException ex) {
          ex.printStackTrace();
      }
  }
}
