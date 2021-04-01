package classLoader;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String className = name.substring(name.lastIndexOf(".") + 1) + ".class";

        ClassLoader systemClassLoader = getSystemClassLoader();
//        SecurityManager securityManager = System.getSecurityManager();
//        System.out.println(securityManager);
        System.out.println("system classLoader is: " + systemClassLoader);
        InputStream in = getClass().getResourceAsStream(className);

        if(in==null){
            return super.loadClass(name);
        }
        byte[] byteArr = null;
        try {
             byteArr = new byte[in.available()];
             in.read(byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,byteArr,0,byteArr.length);

//        return super.loadClass(name, resolve);
    }

    public static void main(String[] args) {
//        ClassLoader myClassloader = new MyClassLoader();
        MyClassLoader myClassloader = new MyClassLoader();

        try {
            Object classLoader = myClassloader.loadClass("classLoader.MyClassLoader").newInstance();

            Class<?> objClass = classLoader.getClass();
            System.out.println(objClass);
            System.out.println(classLoader instanceof classLoader.MyClassLoader);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
