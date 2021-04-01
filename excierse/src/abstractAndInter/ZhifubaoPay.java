package abstractAndInter;

/**
 *
 * @Author: wushuai
 * @Date: 2019/9/12 9:27
 * @Description:
 */
public class ZhifubaoPay extends AbstractPay {

    @Override
    public void pay(int cents) {
        System.out.println("zhifubao pay money:" + cents + "cents");
    }

    @Override
    protected double payMoney() {
        System.out.println("zhifubao pay!");
        return 0;
    }
}
