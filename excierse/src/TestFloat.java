import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class TestFloat {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "gR8NlAXv6y7xyuLa";
        byte[] bytes = str.getBytes();
        StringBuilder strBuilder = new StringBuilder();
        for(byte b:bytes){
            strBuilder.append(b);
        }
        System.out.println(URLEncoder.encode(str,"UTF-8").getBytes());


//        Integer a = -1099396;
//        a+=8;
//        a+=6;
//        System.out.println(a.floatValue());
//
//
//
//        Float x = Float.valueOf("3.2");
//        float y = Float.parseFloat("3.5");

    }
}
