package com.chinamobile.cmss.bcse.tool.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.spellcheck_multi_appliance.Querycorrect;

/**
 * 工程配置文件类
 * 
 * @author Quan
 * 
 */
public class Config {

	// 返回结果
	public static final String RESULT_SUCCESS = "SUCCESS";
	public static final String RESULT_FAIL = "FAIL";
	// 用户停用状态
	public static final String USER_STOP_STATUS = "1";
	// 用户正常状态
	public static final String USER_NORMAL_STATUS = "0";
	
	public static  String ADMIN_PWD = "BCse@2017!@#";
	public static  String MUTIVALUE_SPLIT = "#";
	// 单页可获取最大条数
	public static final int PAGENUM_MAX = 200;
	// 管理员role
	public static final String ROLE_ADMIN = "0";
	// 普通用户role
	public static final String ROLE_COMMON = "1";
	// 用户信息保存到redis中的key前缀
//	public static final String USER_KEY = "_USER";

	// 应用状态
	// 启用
	public static final String APP_NORMAL_STATUS = "0";
	// 暂停
	public static final String APP_PAUSE_STATUS = "1";
	// 禁用
	public static final String APP_STOP_STATUS = "2";
	// 创建中
	public static final String APP_ING_STATUS = "3";
	//需要校验应用状态的接口
	public static final String[] APP_CHECK_STATUS_URL = {"/analyzer","/recovery","/search","/suggestion"
						,"/statistics/hotWord/month","/statistics/hotWord/yesterday","/statistics/hotWord/week"
						,"/index"};
	
	public static String OA_URL;
	// 应用信息保存到redis中的key前缀
//	public static final String APP_KEY = "_APP";

	// 返回信息
	// 成功返回码
	public static final String SUCCESS_CODE = "200";
	public static final String FAIL_SYS_CODE = "1000000";
	public static final String FAIL_SERIVICE_CODE = "2000000";

	public static ApplicationContext ac = new ClassPathXmlApplicationContext("/spring-mybatis.xml");
	public static HashMap<String, Querycorrect> APP_DIC_MAP = new HashMap<String, Querycorrect>();
	public static final String DIC_MODE_STANDARD = "standard";
	public static final String DIC_MODE_COMMON = "common";
	public static final String DIC_SPLIT_FLAG = "###";
	// 异常java文件的目录
	public static final String EXCEPTION_ROOT_PATH = "com.chinamobile.cmss.bcse.web.exception.";

	public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
	public static final String CONTENT_TYPE_FILE = "multipart/form-data";
	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String ENCODE_GBK = "gb18030";
	public static final String ROUTE_FIELD = "_route_";
	public static String redisServerIp;
	public static String redisServerPort;

	public static String ZK_HOST;
	public static String ORINGINAL_CONF; // 索引相关的原始模板配置文件的路径
	public static String CORE_CONFIG_DIR; // 索引相关的生成配置文件的目录
	// 检索使用
	public static String DEFAULT_FIELD;
	public static String DEFAULT_HANDLER;
	public static int MAX_NUM;
	public static String INTEL_ASSOCIATE_PREX;
	public static String INDEX_FIELD_PREX;
	public static String INDEX_SPLIT = "_";
	public static String ROUGH_SORT_PREX;
	public static String FIELD_TYPE;
	public static String UNIQUE_ID;

	// 线下环境zookeeper配置文件在本地存储地址
	public static String ZKCONFIG_LOCALDIR = "zkConfigs";
	// public static String ZKCONFIG_ONLINEDIR = "/home/bcse/zkConfigs";

	// 监控平台的地址
	public static String MONITOR_ADDRESS;

	// 日誌幾口 ip地址
	public static String LOG_ADDRESS;
	public static String MIGU_LOG_ADDRESS;

	public static int SHARD_NUM; // 分片个数
	public static int REPLICATE_NUM; // 备份个数
	public static int MAX_PER_NODE_NUM; // 单个节点最多的核数

	// mysql的连接信息
	// public static String MYSQL_URL =
	// "jdbc:mysql://10.133.5.140:3306/bcse-data?autoReconnect=true";
	public static String MYSQL_URL;
	public static String MYSQL_USERNAME;
	public static String MYSQL_PASSWORD;

	// 邮件发送功能配置信息
	public static String SERVER_HOST;
	public static String SERVER_AUTH;
	public static String SERVER_URL;
	public static String SEND__USER;
	public static String SEND__USER_NAME;
	public static String SEND__USER_PASSWORD;
	public static String BCSE_INFO;

	// servlet、前端统一编码格式
	public static String ENCODING;
	// servlet中，区别不同功能应用的参数key
	public static String METHOD;
	// 随机数据，用于生产随机密码
	public static char[] charSequence;

	// FTP上传文件
	// 保存的 路径
	public static String SAVE_FILE_PATH;

	public static String LOG_FOLDER_NAME;

	// kafka的配置信息
	public static String BROKERS;
	public static int KAFKA_PRODUCER_NUM = 10;
	
	public static int RULE_NUM_LIMIT;
	
	//指定bcse_service地址，防止跨域问题，支持多个地址，中间以英文分号间隔
	public static String CROS_SERVICE_HOST;

	public static String getBROKERS() {
		return BROKERS;
	}

	public static void setBROKERS(String bROKERS) {
		BROKERS = bROKERS;
	}

	public static String getRedisServerIp() {
		return redisServerIp;
	}

	public static void setRedisServerIp(String redisServerIp) {
		Config.redisServerIp = redisServerIp;
	}

	public static String getRedisServerPort() {
		return redisServerPort;
	}

	public static void setRedisServerPort(String redisServerPort) {
		Config.redisServerPort = redisServerPort;
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(redisServerIp);
		System.out.println(RULE_NUM_LIMIT);
	}

	/**
	 * 初始化加载全局变量
	 * 
	 */
	static {
		// final String CONFIG_PATH = "/home/bcse/bcse.properties";
		// final String CONFIG_PATH = "/etc/bc-se/conf/bcse.properties";
		// final String CONFIG_PATH = "d:\\bcse.properties";
		/* final String CONFIG_PATH = "/etc/bc-se/conf/bcse.properties"; */
		Properties pr = null;
		FileInputStream fin = null;
		try {
			String CONFIG_PATH = Config.class.getClassLoader().getResource("bcse.properties").getPath()
					.replaceAll("%20", " ");
			;
			fin = new FileInputStream(CONFIG_PATH);
			pr = new Properties();
			pr.load(fin);
			LoadConfig.iniConfig(pr);
		} catch (IOException e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "loading config error\n" + e.fillInStackTrace());
		} finally {
			try {
				fin.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
