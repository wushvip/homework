/*package com.chinamobile.cmss.bcse.sdk.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.client.ClientProtocolException;

import com.chinamobile.cmss.bcse.sdk.entry.BCSEAnalysis;
import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;

public class ThreadTest {

	static BCSEClient bcseClient = null;

	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();
		test.test();
	}

	public void test() {

		try {
			bcseClient = new BCSEClient("891fedd7f34b44b69c9774e3992225c8",
					"127.0.0.1", 8999, null,"secretAccessKey");
			int taskSize = 500;
			// 创建一个线程池
			ExecutorService pool = Executors.newFixedThreadPool(taskSize);
			String queryString = "熊大";
			// 创建多个有返回值的任务
			List<Future> list = new ArrayList<Future>();
			for (int i = 0; i < taskSize; i++) {
				Callable c = new MyCallable(queryString + i);
				// 执行任务并获取Future对象
				Future f = pool.submit(c);
				// System.out.println(">>>" + f.get().toString());
				list.add(f);
			}
			// 关闭线程池
			pool.shutdown();

			// 获取所有并发任务的运行结果
			for (Future f : list) {
				// 从Future对象上获取任务的返回值，并输出到控制台
				System.out.println(">>>" + f.get().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class MyCallable implements Callable<Object> {
		private String queryString;

		MyCallable(String queryString) {
			this.queryString = queryString;
		}

		public Object call() throws Exception {
			System.out.println(">>>" + queryString + "任务启动");

			String result = "";
			String field = "content";
			BCSEAnalysis analysis = new BCSEAnalysis(field, queryString,
					bcseClient);
			try {
				result = analysis.analysis();
				System.out.println(result);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return queryString + "结果:" + result;
		}
	}
}
*/