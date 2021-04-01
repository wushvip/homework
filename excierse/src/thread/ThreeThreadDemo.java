package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过wait/notify机制实现
 * @Author: wushuai
 * @Date: 2019/9/26 15:13
 * @Description:
 */
public class ThreeThreadDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private  static  Object o1 = new Object();
    private  static  Object o2 = new Object();
    private  static  Object o3 = new Object();

    private static  volatile  boolean t2ThreadStartFlag = false;

    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (o1) {
                        while(atomicInteger.intValue()<99) {
                            System.out.println(Thread.currentThread().getName() + " : " + atomicInteger.getAndAdd(1));
                            while (!t2ThreadStartFlag)  {
                                Thread.sleep(100);
                            }
                            synchronized (o2) {
                                o2.notify();
                            }
                            o1.wait();

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (o2) {
                        if (!t2ThreadStartFlag) {
                            t2ThreadStartFlag = true;
                        }
                        while(atomicInteger.intValue()<99) {
                            o2.wait();
                            System.out.println(Thread.currentThread().getName() + " : " + atomicInteger.getAndAdd(1));
                            synchronized(o1) {
                                o1.notify();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t2");

//        Thread t3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    synchronized (o3) {
//                        while(atomicInteger.intValue()<99) {
//                            o3.wait();
//                            System.out.println(Thread.currentThread().getName() + " : " + atomicInteger.getAndAdd(1));
//                            synchronized (o1) {
//                                o1.notify();
//                            }
//                        }
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"t3");
        t1.start();
        t2.start();
//        t3.start();

        while (Thread.activeCount()>2){
            Thread.yield();
        }

    }
}
