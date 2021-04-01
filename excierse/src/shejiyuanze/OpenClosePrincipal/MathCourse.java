package shejiyuanze.OpenClosePrincipal;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-24 14:42
 * @Description
 * @Since V1.0
 */

/**
 * @Title 数学课堂
 * @Author Administrator
 * @Date 2021-03-24 14:42
 * @Description
 * @Since V1.0
 */
public class MathCourse implements Icourse {

    private static final double MATH_PRICE = 1999.9;

    @Override
    public String getIcourseName() {
        return "math";
    }

    @Override
    public double getPrice() {
        return MATH_PRICE;
    }
}
