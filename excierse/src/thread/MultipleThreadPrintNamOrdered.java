package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wushuai
 * @Date: 2019/9/20 10:15
 * @Description:
 */
public class MultipleThreadPrintNamOrdered {

    // visible by lock
    private static AtomicInteger count = new AtomicInteger();
    private static Lock  lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    private static volatile boolean stop = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(new CountThread1());
        Thread t2 = new Thread(new CountThread2());
        Thread t3 = new Thread(new CountThread3());

        t2.start();
        t3.start();
        t1.start();
    }

    static class CountThread1 implements Runnable {
        static String threadName = "Thread1";
        @Override
        public void run() {
            while (!stop) {
                try {
                    lock.lock();
                    countNum(threadName);
                    condition2.signal();
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static void countNum(String threadName) {
        if (count.get() > 99) {
            stop=true;
        }else {
            System.out.println("thread:" + threadName + ", count:" + (count.getAndIncrement()));
        }
    }

    static class CountThread2 implements Runnable {
        static String threadName = "Thread2";
        @Override
        public void run() {
            while (!stop) {
                try {
                    lock.lock();
                    condition2.await();
                    countNum(threadName);
                    condition3.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class CountThread3 implements Runnable {
        static String threadName = "Thread3";
        @Override
        public void run() {
            while (!stop) {
                try {
                    lock.lock();
                    condition3.await();
                    countNum(threadName);
                    condition1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
