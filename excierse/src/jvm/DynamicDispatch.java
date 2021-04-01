package jvm;

/**
 * 动态分派
 * javap -c jvm/DynamicDispatch.class
 * Compiled from "DynamicDispatch.java"
 * public class jvm.DynamicDispatch {
 *   public jvm.DynamicDispatch();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *     //new Man object
 *        0: new           #2                  // class jvm/DynamicDispatch$Man
 *        3: dup //复制栈顶数据，并且将复制值压入栈顶
 *        //调用实例构造器
 *        4: invokespecial #3                  // Method jvm/DynamicDispatch$Man."<init>":()V
 *        //将栈顶引用数值存入本地变量
 *        7: astore_1
 *        8: new           #4                  // class jvm/DynamicDispatch$Woman
 *       11: dup
 *       12: invokespecial #5                  // Method jvm/DynamicDispatch$Woman."<init>":()V
 *       15: astore_2
 *       16: aload_1
 *       17: ldc           #6                  // String china
 *       19: invokevirtual #7                  // Method jvm/DynamicDispatch$Human.sayHello:(Ljava/lang/String;)V
 *       22: aload_2
 *       23: ldc           #6                  // String china
 *       25: invokevirtual #7                  // Method jvm/DynamicDispatch$Human.sayHello:(Ljava/lang/String;)V
 *       28: new           #4                  // class jvm/DynamicDispatch$Woman
 *       31: dup
 *       32: invokespecial #5                  // Method jvm/DynamicDispatch$Woman."<init>":()V
 *       35: astore_1
 *       36: aload_1
 *       37: ldc           #8                  // String tomorrow!
 *       39: invokevirtual #7                  // Method jvm/DynamicDispatch$Human.sayHello:(Ljava/lang/String;)V
 *       42: return
 * }
 */
public class DynamicDispatch {

    static abstract class Human{
      abstract void sayHello(String name);
    }

    static class Man extends Human {

        @Override
        void sayHello(String name) {
            System.out.println("man say hello :" + name);
        }
    }

    static class Woman extends Human{

        @Override
        void sayHello(String name) {
            System.out.println("woman say hello :" + name);
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Woman();
        man.sayHello("china");
        women.sayHello("china");

        man = new Woman();
        man.sayHello("tomorrow!");
    }
}
