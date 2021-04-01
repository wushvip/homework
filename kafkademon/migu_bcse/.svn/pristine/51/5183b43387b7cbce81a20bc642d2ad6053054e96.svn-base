package com.chinamobile.cmss.bcselogAnalyse.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @ClassName: Config
 * @Description: 一些常量
 * @author: yangjing
 * @date: 2016年3月29日 下午5:11:01
 */
public class Config {
	private static Logger logger = Logger.getLogger(Config.class);
	public static Properties properties = new Properties();

	/**
	 * 初始化加载全局变量
	 * 
	 */
	static {
		// InputStream inputstream=null;
		FileInputStream fin = null;
		try {
			 //final String CONFIG_PATH = "bcselog.properties";
			final String CONFIG_PATH = "/etc/bc-se/bcselog/conf/bcselog.properties";

			fin = new FileInputStream(CONFIG_PATH);
			properties = new Properties();
			properties.load(fin);
		} catch (Exception e) {
			logger.error("不能读取配置文件,请确保bcselog.properties在CLASSPATH指定的路径中", e);
		} finally {
			if (null != fin) {
				try {
					fin.close();
					fin = null;
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	// mysql配置
	public static final String DRIVER = Config.properties.getProperty("driver", "").trim();
	public static final String URL = Config.properties.getProperty("url", "").trim();
	public static final String USER = Config.properties.getProperty("user", "").trim();
	public static final String PASSWORD = Config.properties.getProperty("password", "").trim();

	// 项目根目录
	public static final String PROJECT_HOME = Config.properties.getProperty("project_home", "").trim();
	// jar包名
	public static final String JAR_NAME = Config.properties.getProperty("jar_name", "").trim();

	public static final String USER_NAME = Config.properties.getProperty("USER_NAME", "hdfs");

	// 批量写数据库，一次写入的数量
	public static final int NUM = 100000;
	// redis

	public static final String REDIS_SERVER_IP = Config.properties.getProperty("REDIS_SERVER_IP", "").trim();
	public static final String HOTWORD_KEY = Config.properties.getProperty("HOTWORD_KEY", "").trim();
	public static final int REDIS_SERVER_PORT = Integer
			.parseInt(Config.properties.getProperty("REDIS_SERVER_PORT", "").trim());

	public static final String REDIS_PASSWD = properties.getProperty("REDIS_PASSWD", "").trim();
	
	

	public static void main(String[] args) {

	}

}
