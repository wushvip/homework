package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class JVMTest01 {

    private byte[] bytes = new byte[64*1024];
    public static void main(String[] args) {
        List<JVMTest01> list = new ArrayList<>();
        int index = 0;
        try {
            while (true) {
                Thread.sleep(100);
                list.add(new JVMTest01());
                index++;
            }
        }
        catch(Throwable e){
            System.out.println("current index:" + index);
            e.printStackTrace();
        }
    }
}
