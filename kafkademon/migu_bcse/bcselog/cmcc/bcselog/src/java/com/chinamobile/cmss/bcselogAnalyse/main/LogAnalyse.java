package com.chinamobile.cmss.bcselogAnalyse.main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql.HdfsToMysql;
import com.chinamobile.cmss.bcselogAnalyse.redis.JedisUtil;
import com.chinamobile.cmss.bcselogAnalyse.statistic.CostStatistic;
import com.chinamobile.cmss.bcselogAnalyse.statistic.CountStatistic;
import com.chinamobile.cmss.bcselogAnalyse.statistic.WordStatistic;
import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import com.chinamobile.cmss.bcselogAnalyse.tools.ExecuteShell;
import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;
import com.chinamobile.cmss.bcselogAnalyse.tools.Tools;

/**
 * @ClassName: LogAnalyse
 * @Description: 日志分析全过程（日志分析主节点上运行）
 * @author: yangjing
 * @date: 2016年5月31日 上午10:29:03
 */
public class LogAnalyse {
	private static Logger logger = Logger.getLogger(LogAnalyse.class);
	private String operTime = Tools.getYesterday();
	// apache日志地址
	private String logDir = Config.properties.getProperty("log_dir").trim() + "access_log." + operTime + ".txt";
	// HDFS输入输出目录
	private String mrInput = Config.properties.getProperty("hdfs_home").trim() + "input-data/" + operTime;;
	private String mrOutput = Config.properties.getProperty("hdfs_home").trim() + "output-data/" + operTime;;
	private String mrOutputKeywords = mrOutput + "/keywords";
	private String mrOutputCount = mrOutput + "/count";
	private String mrOutputCost = mrOutput + "/cost";
	private String mrOutputSecondCount = mrOutput + "/secondCount";
	private String mrOutputNumFound = mrOutput + "/numFound";

	// 日志分析结果本地目录
	private String logOutputPath = Config.properties.getProperty("logoutput_path").trim() + operTime;
	private String logOutputPathKeywords = logOutputPath + "/keywords";
	private String logOutputPathCount = logOutputPath + "/count";
	private String logOutputPathCost = logOutputPath + "/cost";
	private String logOutputPathSecondCount = logOutputPath + "/secondCount";
	private String logOutputPathNumFound = logOutputPath + "/numFound";

	public void run() throws IOException, SQLException, InterruptedException {
		logger.info("--------------------------日志分析开始---------------------------");

		File logFile = new File(logDir);
		if (logFile.exists() && logFile.isFile()) {
			logger.info("把日志文件上传到HDFS，上传之前检查目录是否存在，若存在则删除后重新创建");
			String[] cmdarray1 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-rm", "-r",
					mrInput + "/" + "access_log." + operTime + ".txt" };
			ExecuteShell.run(cmdarray1, null, null);
			logger.info("把日志文件上传到HDFS,上传之前检查目录是否存在，若不存在则创建");
			String[] cmdarray2 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-mkdir", "-p", mrInput };
			ExecuteShell.run(cmdarray2, null, null);
			String[] cmdarray3 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-put", logDir, mrInput };
			ExecuteShell.run(cmdarray3, null, null);
		} else {
			logger.warn("apache日志文件不存在:" + logDir);
		}

		String[] cmdarray00 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-ls", mrInput };
		String reString = ExecuteShell.run(cmdarray00, null, null);
		if (null != reString && reString.length() > 0) {
			logger.info("删除HDFS上的输出目录");
			String[] cmdarray4 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-rm", "-r", mrOutput };
			ExecuteShell.run(cmdarray4, null, null);
			logger.info("依次执行MapReduce程序，获得前一天的搜索关键词、搜索频率、搜索耗时统计结果");

			String[] cmdarray5 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "jar",
					Config.PROJECT_HOME + Config.JAR_NAME, "hadoop", "1", mrInput, mrOutput };
			ExecuteShell.run(cmdarray5, null, new File(Config.PROJECT_HOME));

			logger.info("把MapReduce结果下载到本地,下载之前先检查本地是否已存在，如果存在则先删除");
			File file = new File(logOutputPath);
			if (file.exists() && file.isDirectory()) {
				FileUtils.deleteDirectory(file);
			}
			// file.mkdirs();
			String[] cmdarray6 = { "sudo", "-u" + Config.USER_NAME, "mkdir", logOutputPath };
			ExecuteShell.run(cmdarray6, null, null);

			String[] cmdarray8 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-get",
					mrOutputKeywords + "/part-r-00000", logOutputPathKeywords };
			ExecuteShell.run(cmdarray8, null, null);
			String[] cmdarray9 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-get",
					mrOutputCount + "/part-r-00000", logOutputPathCount };
			ExecuteShell.run(cmdarray9, null, null);
			String[] cmdarray10 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-get",
					mrOutputCost + "/part-r-00000", logOutputPathCost };
			ExecuteShell.run(cmdarray10, null, null);
			String[] cmdarray11 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-get",
					mrOutputSecondCount + "/part-r-00000", logOutputPathSecondCount };
			ExecuteShell.run(cmdarray11, null, null);
			String[] cmdarray12 = { "sudo", "-u" + Config.USER_NAME, "hadoop", "fs", "-get", mrOutputNumFound + "/part-r-00000", logOutputPathNumFound };
			ExecuteShell.run(cmdarray12, null, null);
			logger.info("把MR输出文件写入mysql数据库，写入之前先检查mysql上是否已存在该应用前一天的数据，如存在则先删除");

			MysqlConn mysqlConn = new MysqlConn();
			mysqlConn.deleteSQL(
					"delete from HOTWORDS_DAY where date_format(OPER_TIME,'%Y-%m-%d')=date_sub(curdate(),interval 1 day);");
			mysqlConn.getConnection().close();
			/**
			 * 场景:redis需要同步保存mysql中每天最新的昨天、前一周、前一个月的数据。redis中只保存当天脚本统计出来最新的数据
			 * 1.热词统计定时任务，在插入数据库之前将redis中热词的数据通过命令删除(这里只做第一步)
			 * 2.最新统计出来的热词数据插入数据时，通过数据库的触发器再同步到redis中
			 */

			new JedisUtil().DeleteKeysBySearch(Config.REDIS_SERVER_IP, Config.REDIS_SERVER_PORT, Config.HOTWORD_KEY, Config.REDIS_PASSWD);

			HdfsToMysql hdfsToMysql = new HdfsToMysql();
			hdfsToMysql.Process();
		} else {
			logger.info("HDFS上没有要解析的日志：" + mrInput);
		}

		logger.info("更新热词周表、月表");
		WordStatistic.process();
		logger.info("更新搜索次数日表");
		CountStatistic.process();
		logger.info("更新搜索耗时日表");
		CostStatistic.process();

		logger.info("--------------------------------日志分析结束--------------------------");
		logger.info("time is:" + new Date().toString());
	}

}
