package com.gupao.designMode.protoType.simple;

/**
 * 浅复制：对于值类型的成员变量进行值的复制，而对于引用类型只做引用的复制，不作值的复制
 */
public class CloneTest {

    public static void main(String[] args) {
        CloneTarget p = new CloneTarget();
        p.name = "park";
        p.tartget = new CloneTarget();
        try {
          CloneTarget copy = (CloneTarget)p.clone();
          System.out.println(p.tartget);
          System.out.println(copy.tartget);
          System.out.println(p);
          System.out.println(copy);
          System.out.println(p.tartget==copy.tartget);
          System.out.println(p==copy);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
