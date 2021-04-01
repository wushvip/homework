package abstractAndInter;

/**
 * @Author: wushuai
 * @Date: 2019/9/12 10:12
 * @Description:
 */
public class TestAbstract {


    public static void main(String[] args) {
//        TestAbstract testAbstract = new TestAbstract();
        AbstractPay ap = new AbstractPay() {
            @Override
            public void pay(int cents) {

            }
            @Override
            protected double payMoney() {
                return 0;
            }
        };
    }
}
