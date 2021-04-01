/**
 * 
 */
package thread;

/**
 * @author wushuai
 * @date 2018��9��20��
 * @Description	TODO
 */
public class ObjectService {
	public void serviceMethodA(){
		try {
			synchronized (this) {
				System.out.println("A begin time="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("A end   time="+System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void serviceMethodB(){
		synchronized (this) {
			System.out.println("B begin time="+System.currentTimeMillis());
			System.out.println("B end   time="+System.currentTimeMillis());
		}
	}

}
