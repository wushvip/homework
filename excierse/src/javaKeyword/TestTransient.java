package javaKeyword;

import java.io.*;

/**
 * @autor ws
 * @description
 * @date 2021/3/17 10:40
 **/
public class TestTransient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        serializeUser();
        unSerializeUser();
        User user = new User("wike","123456");

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\var\\user1.txt"));
//        user.writeExternal(oos);
//        oos.flush();
//        oos.flush();
//        System.out.println("测试 Externalizable序列化："+oos.toString());


//        File file = new File("D://var/user1.txt)");
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        user.readExternal(ois);
//
//        System.out.println("测试 Externalizable反序列化");


    }


    public static void serializeUser() throws IOException {
        User user = new User("wike","123456");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\var\\user.txt"));
        oos.writeObject(user);
        oos.flush();
        oos.close();

    }



    public static void unSerializeUser() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream( new FileInputStream("D:\\var\\user.txt"));
        User o = (User) ois.readObject();
        System.out.println("unSerializeUser result user");
    }







}
