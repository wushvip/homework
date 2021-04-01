package string_test;

/**
 * @Author: wushuai
 * @Date: 2019/11/21 19:46
 * @Description:
 */
public class Test04 {

    private int a = 8;

    private final String s = "";

    Test04(){
        final String test = "123";


    }


    public static void main(String[] args) {
//        String str = "Bearer 40cbd868-f259-4262-8eb6-481db7c94059";
//        String[] arr = str.split("\\s+");
//        System.out.println(arr[0]);
//        System.out.println(arr[1]);


        System.out.println("today:" + "third" +"\ttomarroy:" + "thur");
        Test04 test04 = new Test04();
        test04.test1();

    }

    public void test1(){
        Inner inner = new Inner();
        inner.test();
        System.out.println(inner.inn);
    }


    public void test2(){
        System.out.println("this is test2");
    }


    class  Inner{

        private String inn = "son";



        private void test(){
            System.out.println(a);
            test2();
        }


    }


}
