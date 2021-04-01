package reflect;

import java.lang.reflect.Field;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class TestReflect {

    public static void main(String[] args) {
        ResQuota resQuota = new ResQuota();
        Field[] fields = ResQuota.class.getDeclaredFields();
        if(fields !=null && fields.length>0){
            for(Field f:fields){
                System.out.println(f);
                try {
                    f.setAccessible(true);
                    System.out.println(f.getFloat(resQuota));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
