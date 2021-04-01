package jvm;

/**
 *静态分派
 * @Author: wushuai
 * @Date: 2019/9/11 17:13
 * @Description:
 */
public class StaticDispatch {

    static abstract class Human{
//      abstract void sayHello(String name);
//
//      public String test(){
//          return null;
//      }
    }
    static class Man extends Human{

//        @Override
//        void sayHello(String name) {
//            System.out.println("man say hello :" + name);
//        }
    }

    static class Woman extends Human{

//        @Override
//        void sayHello(String name) {
//            System.out.println("woman say hello :" + name);
//        }
    }

    public void sayHello(Human human){
        System.out.println("human say hello !");
    }

    public void sayHello(Woman human){
        System.out.println("woman say hello !");
    }

    public void sayHello(Man human){
        System.out.println("man say hello !");
    }


    public static void main(String[] args) {
        StaticDispatch dispatch = new StaticDispatch();
        Human man = new Man();
        Human woman = new Woman();
        dispatch.sayHello(woman);
        dispatch.sayHello(man);
    }
}
