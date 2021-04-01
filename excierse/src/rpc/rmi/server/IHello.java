package rpc.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 11:24
 * @Description
 * @Since V1.0
 */
public interface IHello extends Remote {

    public String say(String name) throws RemoteException;
}
