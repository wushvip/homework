package jvm;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class Demo01 {
//    static int i = 1;
//    static {
//        i = 0;
//        System.out.println(i);
//    }


    public static void main(String[] args) {
        System.out.println("get... " + SuperClass.i);
    }




}

/**
 * 当调用父类的静态变量时，只会触发父类的初始化，不会触发子类初始化操作
 * 1、若定静态变量定义为
 */
class SuperClass{

    static {
        System.out.println("SuperClass init...........");
        i=10;
    }

    SuperClass(){
        System.out.println("create SuperClass Object..............");
    }
    public static final int i;

}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init...........");
    }
    SubClass(){
        super();
        System.out.println("create SubClass Object..............");
    }


}
