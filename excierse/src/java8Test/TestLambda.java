/**
 * 
 */
package java8Test;

import java.util.Arrays;

/**
 * @author wushuai
 * @date 2018年9月28日
 * @Description	TODO
 */
public class TestLambda {
	
	//标准写法
	public void testLambda1(String[] arr) {
		Arrays.sort(arr,(String a,String b)->Integer.compare(b.length(), a.length()));
		for(String a:arr) {
			System.out.println(a);
		}
	}
	
	public static void main(String[] args) {
		TestLambda test = new TestLambda();
	//关于lambda表达式的使用实例
	//第一种
	String[] arr = {"program", "creek", "is", "a", "java", "site"};
		System.out.println(TestLambda.class.getClassLoader());
//return (a < b) ? -1 : ((a == b) ? 0 : 1);
//	Arrays.sort(arr, (a,b) -> Integer.compare(a.length(), b.length()));
//		for(String a:arr) {
//			System.out.println(a);
//		}
//	}

	//第二种
//	test.testLambda1(arr);
	}
}
