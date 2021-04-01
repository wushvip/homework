package com.chinamobile.cmss.bcselogAnalyse.hdfsToMysql;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import com.chinamobile.cmss.bcselogAnalyse.tools.Tools;

/**
 * @ClassName: hdfsToMysql
 * @Description: hdfsToMysql主函数类
 * @author: yangjing
 * @date: 2016年2月1日 上午9:30:30
 */

public class HdfsToMysql {
	private static Logger logger = Logger.getLogger(HdfsToMysql.class);
	// 日志分析结果本地目录
	private String logOutputPath = Config.properties.getProperty("logoutput_path").trim() + Tools.getYesterday();
	private String logOutputPathKeywords = logOutputPath + "/keywords";
	private String logOutputPathCount = logOutputPath + "/count";
	private String logOutputPathCost = logOutputPath + "/cost";
	private String logOutputPathSecondCount = logOutputPath + "/secondCount";

	public void Process() throws SQLException, IOException {

		// 写入一秒请求的峰值
		logger.info("把每秒搜索最大次数写入mysql数据库...");
		long start = System.currentTimeMillis();
		InsertSecondCount isc = new InsertSecondCount(logOutputPathSecondCount);
		isc.process();
		long end = System.currentTimeMillis();
		logger.info("把每秒搜索最大次数写入mysql共花费时间：" + (end - start) + "ms");

		// 关键词
		logger.info("把热词写入mysql数据库...");
		start = System.currentTimeMillis();
		InsertToKeywords itk = new InsertToKeywords(logOutputPathKeywords);
		itk.process();
		end = System.currentTimeMillis();
		logger.info("把热词写入mysql共花费时间：" + (end - start) + "ms");

		// 搜索次数
		logger.info("把搜索次数写入mysql数据库...");
		start = System.currentTimeMillis();
		InsertToSearchCount itsc = new InsertToSearchCount(logOutputPathCount);
		itsc.process();
		end = System.currentTimeMillis();
		logger.info("把搜索次数写入mysql共花费时间：" + (end - start) + "ms");

		// 搜索耗时
		logger.info("把搜索耗时写入mysql数据库...");
		start = System.currentTimeMillis();
		InsertToSearchCost itsco = new InsertToSearchCost(logOutputPathCost);
		itsco.process();
		end = System.currentTimeMillis();
		logger.info("把搜索耗时写入mysql共花费时间：" + (end - start) + "ms");

		// 搜索结果统计
		logger.info("把搜索结果统计写入mysql数据库...");
		start = System.currentTimeMillis();
		InsertToNumFound.process();
		end = System.currentTimeMillis();
		logger.info("把搜索结果统计写入mysql共花费时间：" + (end - start) + "ms");

	}

}
