package thread;
/**
 * ʵ��Runnable�ӿڴ����߳�
 * @author hp
 * 
 */
public class ThreadTest01 implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			try {
//				Thread.currentThread().sleep(1000);
				if (i<5){
					Thread.currentThread().sleep(200);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("currentThread" + i + ":"+ Thread.currentThread());
			
		}
	}
	public static void main(String[] args) {
//		ThreadTest01 test1 = new ThreadTest01();
//		test1.run();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//			}
//		}).start();
		
		
//		Thread t1 = new Thread(new ThreadTest01());
//		Thread t2 = new Thread(new ThreadTest01());
////		t1.setDaemon(true);
//		t1.start();
//		t2.start();
	}
}
