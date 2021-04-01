package thread;

import java.util.Hashtable;
import java.util.Map;

public class TestSychronized {

	public static void main(String[] args) {
		final TestSychronized sychronized_1 = new TestSychronized(); 
		final TestSychronized sychronized_2 = new TestSychronized(); 

		Thread thread1 = new Thread() {
			@Override
			public void run() {
				synchronized(sychronized_1) {
					try {
						System.out.println(this + "----");
						System.out.println(this.getName() + "start...");
						Thread.sleep(100);
						System.out.println(this.getName() + "–—¡À...");
						System.out.println(this.getName() + "end...");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
		};
		thread1.start();
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (sychronized_2) {
					System.out.println("------"+ this);
					System.out.println(Thread.currentThread().getName() + "start...");
					System.out.println(Thread.currentThread().getName() + "end...");
				}
				
			}
		});
		thread2.start();


		Map map = new Hashtable<>();
		map.put(null,1);
//		System.exit(0);
	}

}
