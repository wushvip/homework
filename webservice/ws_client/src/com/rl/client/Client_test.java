package com.rl.client;

import com.rl.stup.Function;
import com.rl.stup.FunctionService;

public class Client_test {

	public static void main(String[] args) {
		//1.����������ʵ㼯�϶���
		FunctionService services = new FunctionService();
		//2.��÷�����ʵ�ָ��������
		Function function = services.getFunctionPort();
		//3.���÷���˵ķ���
		String result = function.transWords("��ѧ��");
		System.out.println(result);
	}

}
