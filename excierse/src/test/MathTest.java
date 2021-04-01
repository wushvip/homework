/**
 * 
 */
package test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author wushuai
 * @date 2018��11��14��
 * @Description	TODO
 */
public class MathTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int total = 0;
//		int m = 11,n=5;
//		System.out.println(m/n);
//		System.out.println(m%n);
//		System.out.println(total += (m/n) + (m%n>0?1:0));
		
//		Object a = "3";
//		Object b = null;
//		Integer aa = (Integer)a;
//		System.out.println(Integer.parseInt(String.valueOf(a)));
//		System.out.println(Integer.parseInt(b.toString()));
//		System.out.println((boolean)1);

		//针对二进制，向左位移
		System.out.println(1 << 4);//16，位移运算2^4
		System.out.println(2 << 4);//16，位移运算2^4
		System.out.println(3 << 4);//16，位移运算2^4

//		for(int i=0;i<10000;i++){
//			Random random = new Random();
//			Integer next = random.nextInt();
//			System.out.println(next*100);
//			System.out.println(next.hashCode() >>> 16);//
//		}

		//针对二进制，与运算
		System.out.println(5&6);

		System.out.println(8&2);

		//针对二进制异或运算
		System.out.println(9^3);

		//右移运算
		System.out.println(2>>4);



//		long openCpu = 889993243l;
//		long total = 1657787999l;
//		BigDecimal basic = new BigDecimal(100);
//		float cpuPercent =  BigDecimal.valueOf(openCpu)
//				.divide(BigDecimal.valueOf(total),2,BigDecimal.ROUND_UP)
//				.multiply(basic)
//				.floatValue();
//		System.out.println(cpuPercent);
	}

}
