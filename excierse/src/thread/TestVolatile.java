package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile�ؼ��ֿ��Ա�֤�Ա��������Ŀɼ�����˳���ԣ����Ǳ�֤����ԭ���ԣ����
 * volatile����ȡ��synchronized�ؼ���
 * 1����ָֹ��������
 * 2����֤��������Ŀɼ���
 * @author hp
 *
 */
public class TestVolatile {

	private static volatile int race = 0;
	
//	private AtomicInteger inc = new AtomicInteger(0);
	//��֤ԭ���Բ��������������ַ���
	//��һ��ʹ��synchronized��ʹ��ReentrantLOCK��ͬ����������,�ڶ���ʹ�÷�����ͬ����  AtomicInteger
//	public synchronized void increase() {
//		inc++;
//	}
	//ʹ��LOCK����ԱȽ�ʱ��ϳ�
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
		System.out.println("��ʱ:" + (endTIme-beginTIme));
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



