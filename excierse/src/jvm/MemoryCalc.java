package jvm;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class MemoryCalc {

    public static void main(String[] args) {
        long max = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("max memory is:" + max);
        System.out.println("totalMemory is :" + totalMemory);
    }
}
