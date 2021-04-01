/**
 * 
 */
package gc;

/**
 * @author wushuai
 * @date 2018Äê9ÔÂ12ÈÕ
 * @Description	TODO
 */
public class GarbageTest02 {
	
	private static GarbageTest02 obj = null;
	
	public void islive() {
		System.out.println("yes,i am still live!");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		GarbageTest02.obj = this;
		System.out.println("finalize method excuted");
	}
	

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		obj = new GarbageTest02();
		
		obj = null;
		System.gc();
		Thread.sleep(500);
		if(obj != null) {
			obj.islive();
		}else {
			System.out.println("obj has dead!");
		}
		
		obj = null;
		System.gc();
		Thread.sleep(500);
		if(obj != null) {
			obj.islive();
		}else {
			System.out.println("obj has dead!");
		}
	}

}
