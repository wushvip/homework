/**
 * 
 */
package json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hp
 * @date 2018��8��10��
 * @Description	TODO
 */
public class TestJsonObj {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
//		String a = "3.0";
//		String b = "4";
//		System.out.println(String.valueOf(Float.parseFloat(a) - 1f));
//		System.out.println(String.valueOf(Float.parseFloat(b) - 1.5f));
//
//		Integer c = null;
//		System.out.println(c.intValue());

		final int start = Integer.MAX_VALUE - 100;
		final int end = Integer.MAX_VALUE;
		System.out.println(start);
		System.out.println(end);
		int count = 0;
		for (int i = start; i <= end; i++)
			count++;
		System.out.println(count);



	}


}
