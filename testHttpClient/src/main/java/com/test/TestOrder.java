package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestOrder {
    public static void main(String[] args) {
        List<Person> plist = new ArrayList<Person>();
        //创建3个Person对象，年龄分别是32、20、25，并将他们依次放入List中
        Person p1 = new Person("0001","zhangsan",32);
        Person p2 = new Person("0002","lisi",20);
        Person p3 = new Person("0003","wangwu",25);
        plist.add(p1);
        plist.add(p2);
        plist.add(p3);
        for(Person p:plist){
            System.out.println("排序前的结果："+p.toString());
        }
        Collections.sort(plist, new Comparator<Person>(){
            /*
             * int compare(Person p1, Person p2) 返回一个基本类型的整型，
             * 返回负数表示：p1 小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
            public int compare(Person p1, Person p2) {
                //按照Person的年龄进行升序排列
                if(p1.getAge() > p2.getAge()){
                    return -1;
                }
                if(p1.getAge() == p2.getAge()){
                    return 0;
                }
                return 1;
            }
        });
        for(Person p:plist){
            System.out.println("排序后的结果："+p.toString());
        }
    }
}
