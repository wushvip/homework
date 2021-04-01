package com.chinamobile.cmss.bcse.sdk.search;

import java.util.HashMap;
import java.util.Map;

import com.chinamobile.cmss.bcse.sdk.entry.BCSETestQQ;

public class Testqq extends Thread{
	private BCSETestQQ qq ;
	private int m;
	
	public Testqq(BCSETestQQ qq,int m) {
		System.out.println("init");
		this.qq=qq;
		this.m=m;
	}
	@Override
	public void run() {
		System.out.println("com");
		try {
			 for (int i = 0; i < 1000; i++) {
				 System.out.println("线程"+m+"****************执行请求次数:"+i);
					Map<String, Object> opts = new HashMap<String, Object>();
					String result=qq.testQQ(opts);
					
//					System.out.println(result);
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
