package string_test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class TestRegix {
    public static void main(String[] args) {
        /**
         * \t比哦啊是tab    \n表示换行
         * 常见的转义字符：
         * \n 回车(\u000a)
         * \t 水平制表符(\u0009)
         * \b 空格(\u0008)
         * \r 换行(\u000d)
         * \f 换页(\u000c)
         * \' 单引号(\u0027)
         * \" 双引号(\u0022)
         * \\ 反斜杠(\u005c
         */

//        String str = "";
//        System.out.println(Float.valueOf(str));


//        String url = "http://10.154.7.160:8100/bdoc/v2/renter/users/23/logout";
//        System.out.println(url.endsWith("/logout"));
//        System.out.println(url.contains("logout"));

//        String pattern = "^[a-zA-Z]([A-Za-z0-9_\\-]{0,20})$";
//        String pattern = "^[\u4e00-\u9fa5]*|\\w*|\\d*|_*|-*$";
//        String pattern = "^[a-zA-Z0-9_u4e00-u9fa5]+$";

        // [a-zA-Zu4e00-u9fa5][a-zA-Z0-9u4e00-u9fa5]+
        String regex = "^[a-zA-Z\\\\u4e00-\\\\u9fa5][-a-zA-Z0-9_\\\\u4e00-\\\\u9fa5]+$";


//        String pattern = "^[A-Za-z]([A-Za-z0-9_-]{0,17})([\\\\u4e00-\\\\u9fa5]{0,10})$";
//        String pattern = "^[\\w\\-－＿[0-9]\\u4e00-\\u9fa5\\uFF21-\\uFF3A\\uFF41-\\uFF5A]+$";

//        String REGEX_CHINESE = "[\\u4e00-\\u9fa5]+";

        System.out.println(Pattern.matches(regex,"苏研"));
        System.out.println(Pattern.matches(regex,"苏小研"));
        System.out.println(Pattern.matches(regex,"苏州之家"));
        System.out.println(Pattern.matches(regex,"杭州"));
        System.out.println(match(regex,"dddfjkoiiiklll"));
        System.out.println(match(regex,"dddfjkoiiikl*&"));
        System.out.println(match(regex,"dddfjkoiiikl-123"));
//        System.out.println(Pattern.matches(pattern, "567888"));
//        System.out.println(Pattern.matches(pattern, "sddffffgghh"));
//        System.out.println(Pattern.matches(pattern, "rtyyyy-"));
//        System.out.println(Pattern.matches(pattern, "rtyyyy_mmm"));
    }


    /**
     * 验证小写字母
     *
     * @param 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLowChar(String str) {
        String regex = "^[a-z]+$";
        return match(regex, str);
    }

    /**
     * 验证验证输入字母
     *
     * @param 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLetter(String str) {
        String regex = "^[A-Za-z]+$";
        return match(regex, str);
    }


    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
