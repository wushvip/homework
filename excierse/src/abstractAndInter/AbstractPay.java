package abstractAndInter;

/**
 * 1、类中方法生命为abstract，那么当前类一定要加上abstract修饰,反过来，如果当前类声明为abstract,那么类中的方法不一定为abstract
 * 2、抽象类中可以定义抽象方法以及具体的方法
 *
 * @Author: wushuai
 * @Date: 2019/8/12 23:13
 * @Description:
 */
public abstract class AbstractPay implements Payment {
    protected static int a;
    static {
        a=1;
    };

    protected abstract double payMoney();

    void doPay(){
        a=3;
        this.payMoney();
        System.out.println(a);
    }


    private String getName(){
        return "aaa";
    }

    public static void main(String[] args) {
        AbstractPay pay = new ZhifubaoPay();
        pay.doPay();
    }
}
