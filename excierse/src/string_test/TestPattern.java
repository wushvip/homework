package string_test;

import java.util.regex.Pattern;

/**
 * @Author: wushuai
 * @Date: 2019/10/25 15:14
 * @Description:
 */
public class TestPattern {

    public static void main(String[] args) {
        String regex = "^[a-zA-Z\u4e00-\u9fa5][-a-zA-Z0-9_\u4e00-\u9fa5]+$";


//        System.out.println(Pattern.matches(regex,"小猪"));
//        System.out.println(Pattern.matches(regex,"苏小研"));
//        System.out.println(Pattern.matches(regex,"苏州之家"));
        System.out.println(Pattern.matches(regex,"杭州"));
        System.out.println(Pattern.matches(regex,"dddfjkoiiiklll"));
        System.out.println(Pattern.matches(regex,"dddfjkoiiikl*&"));
        System.out.println(Pattern.matches(regex,"dddfjkoiiikl-123"));
        System.out.println(Pattern.matches(regex,"456dddfjkoiiikl-123"));

        System.out.println(System.currentTimeMillis());
    }
}
