package abstractAndInter;

/**
 * 接口与抽象类的区别
 * 1、接口中不能包含构造器，而抽象类中可以。
 * 2、接口可以被继承、也可以被实现，而抽象类只能被继承。一个雷可以实现多个接口，但是只能继承一个类。
 * 3、接口和抽象类中都可定义静态变量，但是接口中静态变量只能定义为public static,默认。
 * 4、java8之后，接口中可以定义具体的方法实现，在此之前，只能定义抽象方法。
 *
 * 5、抽象类主要在应用程序中用来定义公共方法与模板方法，实现子类的复用
 *      接口主要的作用用来提供框架内部模块之间的相互通信。降低程序直接的耦合度，易于扩展（比如jdbc定义了一套接口规范，数据库各厂商根据这一套规范有自己的具体实现（驱动jar））
 * 6\接口中只有含有抽象方法（静态方法必须要实现）或者不可变常量。
 * 7、抽象类抽象类别，接口主要用来抽象功能
 * @Author: wushuai
 * @Date: 2019/8/12 23:10
 * @Description:
 */
 interface Payment {

     public static String var = "adc";

     static void test(){
         System.out.println("------");
     };


    //编译不通过
//   private void pay(int cents);
//   protected void pay(int cents);
    //编译通过，interfance中接口方法只能被public或者默认修饰
//    void pay(int cents);
    public void pay(int cents);


    default boolean isSuccess(){
        return false;
    }

    default String getPayInfo(int id){
        return "";
    }
}
