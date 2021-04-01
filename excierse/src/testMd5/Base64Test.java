package testMd5;

import java.io.UnsupportedEncodingException;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class Base64Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String author = "admin:1siam9mNBFvv2TJvKdw/Nw==";
        System.out.println();
        System.out.println(author.getBytes("UTF-8"));
//        System.out.println(Base64.en);
    }
}
