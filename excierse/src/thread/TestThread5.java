package thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wushuai
 * @Date: 2019/9/19 17:17
 * @Description:
 */
public class TestThread5 {

    private Object lock = new Object();

    private boolean RUN0 = true;

    private static final int LIMIT = 33;


    private int NUM = 0;


    public static void main(String[] args) throws InterruptedException, IOException {

        AtomicInteger finalI = new AtomicInteger(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread t1 = new Thread(() -> System.out.println("t1:" + finalI.getAndIncrement()));
        Thread t2 = new Thread(() -> System.out.println("t2:" + finalI.getAndIncrement()));
        Thread t3 = new Thread(() -> System.out.println("t3:" + finalI.getAndIncrement()));
        for (int i = 0; i < 33; i++) {
            executorService.execute(t1);
            executorService.execute(t2);
            executorService.execute(t3);
        }
//        System.in.read();
//        final TestThread5 o = new TestThread5();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    o.m0();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, "t0").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    o.m1();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, "t1").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    o.m2();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, "t2").start();
//
//        Thread.sleep(10 * 1000);

    }

    private void m1() throws InterruptedException {

        for (int i = 1; i < LIMIT; i++) {

            synchronized (lock) {
                if ((NUM+1)%3==0) {
                    lock.wait();
                }

                System.out.println(Thread.currentThread().getName() + "___" + (++NUM));

//                RUN0 = true;

                lock.notify();

            }

        }

    }
    private void m2() throws InterruptedException {

        for (int i = 2; i < LIMIT; i++) {

            synchronized (lock) {
                if ((NUM+1)%3==2) {
                    lock.wait();
                }

                System.out.println(Thread.currentThread().getName() + "___" + (++NUM));

//                RUN0 = true;

                lock.notify();

            }

        }

    }

    private void m0() throws InterruptedException {

        for (int i = 0; i < LIMIT; i += 3) {

            synchronized (lock) {

                if ((NUM+1)%3==1) {

                    lock.wait();
                }

                System.out.println(Thread.currentThread().getName() + "___" + (++NUM));

//                RUN0 = false;

                lock.notify();

            }

        }

    }
}
