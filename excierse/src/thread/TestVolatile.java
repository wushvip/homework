package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile关键字可以保证对变量操作的可见性与顺序性，但是保证不了原子性，因此
 * volatile不能取代synchronized关键字
 * 1、禁止指令重排序
 * 2、保证共享变量的可见性
 * @author hp
 *
 */
public class TestVolatile {

	private static volatile int race = 0;
	
//	private AtomicInteger inc = new AtomicInteger(0);
	//保证原子性操作，有以下三种方案
	//第一种使用synchronized、使用ReentrantLOCK（同步互斥锁）,第二种使用非阻塞同步锁  AtomicInteger
//	public synchronized void increase() {
//		inc++;
//	}
	//使用LOCK锁相对比较时间较长
//	Lock lock = new ReentrantLock();
	public static void increase() {
//		lock.lock();
//		inc.getAndIncrement();
		race++;
//		lock.unlock();
	}
	public static void main(String[] args) {
		long beginTIme = System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			new Thread() {
				public void run() {
					for(int j=0;j<10000;j++) {
						increase();
					}
				};
			}.start();
		}
		
		while(Thread.activeCount()>1) {
			System.out.println("current active thread num:" + Thread.activeCount());
			Thread.currentThread().getThreadGroup().list();
			Thread.yield();
		}
		long endTIme = System.currentTimeMillis();
		System.out.println("耗时:" + (endTIme-beginTIme));
//		System.out.println(test.inc);
		System.out.println(race);
	}

	public class TestVolatitle2{
		private volatile boolean stopFlag = false;

		private int i = 0;

		public void shutdown(){
			stopFlag = true;
		}

		public void excute(String tail){
			while (!stopFlag){

			}
		}
	}

}



