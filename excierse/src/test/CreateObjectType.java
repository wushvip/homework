package test;

/**
 * @Author: wushuai
 * @Date: 2019/9/12 10:22
 * @Description:
 */
public class CreateObjectType {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //1\ new Object();
        
        //2\ 反射
        Class<?> clazz = (Class<?>) Class.forName("test.CPUTest");
        Object o = clazz.newInstance();
        System.out.println(o);

        //3、工厂模式
        String s = String.valueOf(2);
        System.out.println(s);

        //4、clone()



        //5、反序列化方式
    }
}
