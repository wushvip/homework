package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wushuai
 * @Date: 2019/9/17 20:18
 * @Description:
 */
public class ThreadTest04 {

    private static volatile boolean flag = false;
    private static volatile boolean isRunning = false;

    private static Object obj = new Object();

//    private static AtomicInteger i = new AtomicInteger(0);

//    public static void increase(){
//        i.getAndIncrement();
//    }

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(1);

        Thread t1 =  new Thread(()-> System.out.println("thread-a" + "----" + i.getAndIncrement()), "thread-a");
        Thread t2 =  new Thread(()-> System.out.println("thread-b"+ "----" + i.getAndIncrement()), "thread-b");
        Thread t3 =  new Thread(()-> System.out.println("thread-c" + "----" + i.getAndIncrement()), "thread-c");

        //三个线程交替遍历1-99整数，可以利用singleThreadExcutor的排队执行特性
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for(int j=0;j<33;j++){
            threadPool.execute(t1);
            threadPool.execute(t2);
            threadPool.execute(t3);
        }

//        while (threadPool.){
//
//        }
    }

}
