package string_test;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class TestStringToInt {

    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("version1"));
        String s = "version1";
        String newStr = s.replace("version", "");
        System.out.println(Integer.parseInt(newStr));

    }
}
