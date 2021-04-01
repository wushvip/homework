package com.rl.client;

import com.rl.stup.Function;
import com.rl.stup.FunctionService;

public class Client_test {

	public static void main(String[] args) {
		//1.创建服务访问点集合对象
		FunctionService services = new FunctionService();
		//2.获得服务访问点指定的类名
		Function function = services.getFunctionPort();
		//3.调用服务端的方法
		String result = function.transWords("张学良");
		System.out.println(result);
	}

}
