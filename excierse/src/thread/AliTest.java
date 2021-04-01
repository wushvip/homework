package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wushuai
 * @Date: 2019/9/20 10:18
 * @Description:
 */
public class AliTest {

    private static Lock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();
    private static Integer count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new RunTask(),"t1");
        Thread thread2 = new Thread(new RunTask(),"t2");
        Thread thread3 = new Thread(new RunTask(),"t3");

        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        thread3.start();

    }

    static class RunTask implements Runnable{

        @Override
        public void run() {
            lock.lock();
            try{
                while(count<100) {
                    System.out.println(Thread.currentThread().getName() + "--->" + ++count);
                    condition.signal();
                    condition.await();
                }
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }
    }
}
