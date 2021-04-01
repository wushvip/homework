package rpc.rmi.client;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 12:37
 * @Description
 * @Since V1.0
 */

import rpc.rmi.server.IHello;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Title RMI客户端
 * @Author Administrator
 * @Date 2021-03-25 12:37
 * @Description
 * @Since V1.0
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            IHello hello = (IHello) Naming.lookup("rmi://localhost:8888/RmiHello");
            System.out.println(hello.say("wike"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
