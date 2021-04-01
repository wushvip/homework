package main.common;

import org.omg.CORBA.INTERNAL;

public class Test2 {
    public static void main(String[] args) {
        String s1 = "false";
        String s2 = "true";
        String s3 = "fAlSe";
        String s4 = "TrUe";
        String s5 = "true_a";

//        System.out.println((Boolean)s1);
        System.out.println(Boolean.getBoolean(s2));
        System.out.println(Boolean.getBoolean(s3));
        System.out.println(Boolean.getBoolean(s4));
        System.out.println(Boolean.getBoolean(s5));
        Float a0 = 1.8f;
        float a = a0.floatValue();
//        if(a instanceof Float){
//            System.out.println("yes");
//        }
//        int a1 = ((Double) a).intValue();
//        int a1 = Integer.valueOf(a);
        int a1 = (int)a;
//        if(a instanceof Float){
//            a1 = Integer.parseInt(String.valueOf(a));
//        }
        System.out.println(a1);

        System.out.println(String.valueOf(a));

    }
}
