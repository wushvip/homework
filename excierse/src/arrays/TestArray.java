package arrays;

import java.util.Arrays;
import java.util.List;

public class TestArray {

	public static void main(String[] args) {
//		String str = "qqq";
//		List<String> lists = Arrays.asList(str);
//		for(String l:lists) {
//			System.out.println(l);
//		}
//
//		//���鴴�����ʼ��
//		//1����һ��10����������
//		int[] arr1s = new int[10];
//		arr1s[0] = 0;
//		arr1s[1] = 1;
//		arr1s[2] = 2;
//		arr1s[3] = 3;
//		arr1s[4] = 4;
//		arr1s[5] = 5;
//		arr1s[6] = 6;
//		arr1s[7] = 7;
//		System.out.println(arr1s[1]);
//
//		//2����
//		String[] arr2 = new String[] {"a","b"};
//		System.out.println(arr2[0]);
//
		List<? extends Number> list1 = Arrays.asList(1,2,3,4,5.5);
		System.out.println(list1.toString());

//
//		int[] arr3 = {1,2,3,5,5};


		byte[] bytes = new byte[3];
		System.out.println(new String(bytes));
		
		
	}

}