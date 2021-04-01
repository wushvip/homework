package rpc.rmi.server;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-25 11:21
 * @Description
 * @Since V1.0
 */

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Title 服务端程序
 * @Author Administrator
 * @Date 2021-03-25 11:21
 * @Description 创建RMI注册表，启动RMI服务，并将远程对象注册到RMI注册表
 * @Since V1.0
 */
public class RMIServer {


    public static void main(String[] args) {
        IHello rmiHello = null;
        try {
            rmiHello = new IHelloImpl();
            //gei yuan
            LocateRegistry.createRegistry(8888);

            Naming.bind("rmi://localhost:8888/RmiHello",rmiHello);
            System.out.println("远程服务发布suceess！！！");
        }catch (RemoteException e) {
            System.out.println("创建远程对称异常");
            e.printStackTrace();
        }catch (AlreadyBoundException e) {
            System.out.println("重新绑定异常");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("URL畸形异常");
            e.printStackTrace();
        }

    }

}
