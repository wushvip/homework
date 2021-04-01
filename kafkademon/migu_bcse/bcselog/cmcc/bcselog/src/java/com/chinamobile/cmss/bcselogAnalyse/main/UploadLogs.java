package com.chinamobile.cmss.bcselogAnalyse.main;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import com.chinamobile.cmss.bcselogAnalyse.tools.ExecuteShell;
import com.chinamobile.cmss.bcselogAnalyse.tools.Tools;

/**
 * @ClassName: UploadLogs
 * @Description: 把apache日志上传到hdfs（日志分析从节点上运行）
 * @author: yangjing
 * @date: 2016年5月31日 上午10:30:11
 */
public class UploadLogs {
	private static Logger logger = Logger.getLogger(UploadLogs.class);
	private String operTime = Tools.getYesterday();
	// apache日志地址
	private String logDir = Config.properties.getProperty("log_dir").trim() + "access_log." + operTime + ".txt";
	// 日志上传到HDFS后的文件名
	private String logName = "access_log." + operTime + "-2.txt";
	// HDFS输入目录
	private String mrInput = Config.properties.getProperty("hdfs_home").trim() + "input-data/" + operTime;

	public void run() throws IOException, InterruptedException {
		File logFile = new File(logDir);
		if (logFile.exists() && logFile.isFile()) {
			logger.info("把日志文件上传到HDFS，上传之前检查目录是否存在，若存在则删除后重新创建");
			String[] cmdarray1 = { "sudo", "-uhdfs", "hadoop", "fs", "-rm", "-r", mrInput + "/" + logName };
			ExecuteShell.run(cmdarray1, null, null);
			String[] cmdarray2 = { "sudo", "-uhdfs", "hadoop", "fs", "-mkdir", "-p", mrInput };
			ExecuteShell.run(cmdarray2, null, null);
			String[] cmdarray3 = { "sudo", "-uhdfs", "hadoop", "fs", "-put", logDir, mrInput + "/" + logName };
			ExecuteShell.run(cmdarray3, null, null);
		} else {
			logger.warn("apache日志文件不存在:" + logDir);
		}

	}

}
