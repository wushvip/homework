package com.test;

public class String_test {
    public void change(String name){
        name = name + "hello";
        System.out.println("更改后hashcode:" + name.hashCode());
    }

    public static void main(String[] args) {
//        String_test test = new String_test();
//        String name = "tom";
//        System.out.println("更改前hashcode:" + name.hashCode());
//        test.change(name);
//        System.out.println(name);
//
//
//        int s = 0;
//        System.out.println(s);


        boolean flag1 = true;
        boolean flag2 = true;
        int a =0;
        if(flag1){
            a = 1;
        }else if(flag2){
            a = 2;
        }else{
            a = 3;
        }
        System.out.println(a);
    }
}
