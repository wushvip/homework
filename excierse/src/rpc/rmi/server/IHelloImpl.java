package rpc.rmi.server;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 11:26
 * @Description
 * @Since V1.0
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 11:26
 * @Description
 * @Since V1.0
 */
public class IHelloImpl  extends UnicastRemoteObject implements IHello{


    protected IHelloImpl() throws RemoteException {
        super();
    }

//    protected IHelloImpl(int port) throws RemoteException {
//        super(port);
//    }
//
//    protected IHelloImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
//        super(port, csf, ssf);
//    }

    @Override
    public String say(String name) {
        System.out.println("invoke say()……");
        return "hello , " + name;
    }
}
