package shejiyuanze.OpenClosePrincipal;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-24 14:44
 * @Description
 * @Since V1.0
 */

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-24 14:44
 * @Description
 * @Since V1.0
 */
public class TestDesign {

    public static void main(String[] args) {
        Icourse icourse = new MathDiscountIcourse();

        System.out.println("icourseName: " + icourse.getIcourseName() + "\n" +
                            "price: " + icourse.getPrice());
    }
}
