package staticAndFinal;

/**
 * @Author: wushuai
 * @Date: 2019/12/11 12:47
 * @Description:
 */
public class SubClass extends SuperClass {


    static {
        System.out.println("子类静态代码块");
    }

    public SubClass(){
        System.out.println("子类构造器");
    }
}
