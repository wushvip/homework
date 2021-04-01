package com.chinamobile.cmss.bcselogAnalyse.main;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.hadoop.JobRun;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		if (null == args || args.length == 0) {
			System.out.println("请选择要运行的任务：");
			System.out.println("启动日志分析定时任务(主节点)请输入：java -jar bcselog.jar start-master");
			System.out.println(
					"运行hadoop任务请输入:sudo -uhdfs hadoop jar bcselog.jar hadoop {keywords|count|cost} input-dir output-dir ");
			return;
		}

		if (null != args && args.length > 0) {
			switch (args[0].toLowerCase()) {
			case "start-master":
				// 启动主节点
				LogAnalyse logAnalyse = new LogAnalyse();
				try {
					logAnalyse.run();
				} catch (Exception e1) {
					logger.error(e1);
					e1.printStackTrace();
				}
				break;

			case "hadoop":
				// 执行hadoop任务
				try {
					JobRun.Process(args);
				} catch (Exception e) {
					logger.error(e);
				}
				break;

			case "start-slave":
				UploadLogs uploadLogs = new UploadLogs();
				try {
					uploadLogs.run();
				} catch (Exception e) {
					e.printStackTrace();
				}

			default:
				System.out.println("第一个参数只能是start-master,或者hadoop任务");
				break;
			}

		}

	}

}
