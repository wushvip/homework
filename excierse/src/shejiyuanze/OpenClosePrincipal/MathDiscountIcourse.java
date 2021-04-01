package shejiyuanze.OpenClosePrincipal;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-24 14:52
 * @Description
 * @Since V1.0
 */

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-24 14:52
 * @Description
 * @Since V1.0
 */
public class MathDiscountIcourse extends MathCourse{

    @Override
    public double getPrice() {
        return super.getPrice()*0.9;
    }
}
