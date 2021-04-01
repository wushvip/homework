package staticAndFinal;

/**
 * @Author: wushuai
 * @Date: 2019/12/11 12:45
 * @Description:
 */
public class SuperClass {

    static {
        System.out.println("父类静态代码块");
    }

    public SuperClass(){
        System.out.println("父类构造器");
    }


    public static void main(String[] args) {
        SubClass sub = new SubClass();
        System.out.println("----------------");
        SubClass sub1 = new SubClass();

    }
}
