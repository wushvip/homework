package thread;
/**
 * �̳�thread�࣬��дrun����
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
			
			//���ֱ�ӵ���run���������������߳��У�û�п������̣߳�
			t1.run();
			
			t1.sleep(2000);
		}
	}
}
