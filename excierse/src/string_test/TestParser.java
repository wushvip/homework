package string_test;

/**
 * @Author: wushuai
 * @Date: 2019/9/18 17:35
 * @Description:
 */
public class TestParser {
    public static void main(String[] args) {
        float a = 3 + Float.parseFloat("10000000000001");
        System.out.println(a);
        if(a>5000000){
            System.out.println("excess total！");
        }
        System.out.println(Long.parseLong("10000000000001"));


    }
}
