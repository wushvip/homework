package thread;
/**
 * 继承thread类，重写run方法
 * @author hp
 *
 */
public class ThreadTest02 extends Thread{
	
	@Override
	public void run() {
		super.run();
		System.out.println("current:" + Thread.currentThread());
	}
	
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<10;i++) {
			Thread t1 = new ThreadTest02();
			t1.start();
			
			//如果直接调用run方法，运行在主线程中，没有开启新线程，
			t1.run();
			
			t1.sleep(2000);
		}
	}
}
