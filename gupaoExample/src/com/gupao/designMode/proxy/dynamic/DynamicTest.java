package com.gupao.designMode.proxy.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DynamicTest {
    public static void main(String[] args) {
        /**
         * 通过创建一个extends Proxy类并且实现代理接口的子类，来实现jdk动态代理。
         */
        LieTouCompanyProxy lietou = new LieTouCompanyProxy(new ChinaMobile());
        QiYe proxy  =(QiYe) lietou.getInstance();
        proxy.find();


        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", ChinaMobile.class.getInterfaces());
        String fileName = "CompanyProxy.class";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
