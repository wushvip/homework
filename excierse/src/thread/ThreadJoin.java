package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wushuai
 * @Date: 2019/9/20 9:50
 * @Description:
 */
public class ThreadJoin {

    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        int a = 0;

        boolean run = true;
        Thread t1 = new Thread(()-> System.out.println("t1: " + num.getAndIncrement()));
        Thread t2 = new Thread(()-> System.out.println("t2: " + num.getAndIncrement()));
        Thread t3 = new Thread(()-> System.out.println("t3: " + num.getAndIncrement()));

        while (run){
            t1.start();
            t1.join();

            t2.start();
            t2.join();

            t3.start();
            t3.join();

            if(num.intValue()>=99){
                run = false;
            }

        }
    }
}
