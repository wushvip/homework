/**
 * 
 */
package gc;

/**
 * @author wushuai
 * @date 2018Äê9ÔÂ12ÈÕ
 * @Description	TODO
 */
public class GarbageTest01 {
	
	private Object instance = null;
	
	private static final int __1M= 1024*1024;
	
	private byte[] maxSize = new byte[2 * __1M];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GarbageTest01 test01 = new GarbageTest01();
		GarbageTest01 test02 = new GarbageTest01();
		test01.instance = test02;
		test02.instance = test01;
		
		test01 = null;
		test02 = null;
		
		System.gc();
	}

}
