package com.gupao.designMode.test;

import com.gupao.designMode.singleton.seriable.Seriable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SeriableTest {
    public static void main(String[] args) {
        Seriable s1 = Seriable.getInstance();
        Seriable s2 = null;
        try{
            FileOutputStream fos = new FileOutputStream("D:\\seriable.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);
            oos.flush();
            fos.close();
            oos.close();

            FileInputStream fis = new FileInputStream("D:\\seriable.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s2 = (Seriable) ois.readObject();
            ois.close();
            fis.close();

            System.out.println(s1==s2);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
