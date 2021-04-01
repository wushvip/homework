package com.chinamobile.cmss.bcse.tool.config;

import java.io.File;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

public class LoadConfig {

	public static void main(String[] args) {
		String s = LoadConfig.class.getResource("/config.xml").getFile().toString();
		System.out.println(s);
	}

	/**
	 * 读取xml文件
	 * 
	 * @param xmlFilePath
	 * @return Document
	 * 
	 */
	public static Document getXmlDocumentFromFile(String xmlFilePath) {
		Document document = null;
		if (null != xmlFilePath) {
			SAXReader reader = new SAXReader();
			try {
				document = reader.read(new File(xmlFilePath));
			} catch (Exception e) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "initial config error\n" + e.getMessage());
			}
		}
		return document;
	}

	/***
	 * 加载配置文件
	 * 
	 * @param Properties
	 */
	public static void iniConfig(Properties pr) {

		if (pr == null) {
			return;
		}
		Config.KAFKA_PRODUCER_NUM = Integer.parseInt(pr.getProperty("KAFKA_PRODUCER_NUM").trim());
		Config.BROKERS = pr.getProperty("BROKERS", "").trim();
		String node = pr.getProperty("REDIS_SERVER_IP");
		if (null != node) {
			Config.redisServerIp = node.trim();
		}
		
		
		
		node = pr.getProperty("MUTIVALUE_SPLIT");
		if (null != node) {
			Config.MUTIVALUE_SPLIT = node.trim();
		}
		node = pr.getProperty("MIGU_LOG_ADDRESS");
		if (null != node) {
			Config.MIGU_LOG_ADDRESS = node.trim();
		}
		node = pr.getProperty("OA_URL");
		if (null != node) {
			Config.OA_URL = node.trim();
		}
		
		node = pr.getProperty("REDIS_SERVER_PORT");
		if (null != node) {
			Config.redisServerPort = node.trim();
		}
		node = pr.getProperty("ZK_HOST");
		if (null != node) {
			Config.ZK_HOST = node.trim();
		}
		node = pr.getProperty("CROS_SERVICE_HOST");
		if (null != node) {
			Config.CROS_SERVICE_HOST = node.trim();
		}
		
		node = pr.getProperty("ORINGINAL_CONF");
		if (null != node) {
			Config.ORINGINAL_CONF = node.trim();
		}
		node = pr.getProperty("DEFAULT_FIELD");
		if (null != node) {
			Config.DEFAULT_FIELD = node.trim();
		}
		node = pr.getProperty("DEFAULT_HANDLER");
		if (null != node) {
			Config.DEFAULT_HANDLER = node.trim();
		}
		node = pr.getProperty("ADMIN_PWD");
		if (null != node) {
			Config.ADMIN_PWD = node.trim();
		}
		
		
		node = pr.getProperty("MAX_NUM");
		if (null != node) {
			try {
				Config.MAX_NUM = Integer.parseInt(node.trim());
			} catch (Exception e) {
				e.printStackTrace();
				Config.MAX_NUM = 100;
			}
		}
		node = pr.getProperty("INTEL_ASSOCIATE_PREX");
		if (null != node) {
			Config.INTEL_ASSOCIATE_PREX = node.trim();
		}
		node = pr.getProperty("INDEX_FIELD_PREX");
		if (null != node) {
			Config.INDEX_FIELD_PREX = node.trim();
		}
		node = pr.getProperty("ROUGH_SORT_PREX");
		if (null != node) {
			Config.ROUGH_SORT_PREX = node.trim();
		}
		node = pr.getProperty("FIELD_TYPE");
		if (null != node) {
			Config.FIELD_TYPE = node.trim();
		}
		node = pr.getProperty("UNIQUE_ID");
		if (null != node) {
			Config.UNIQUE_ID = node.trim();
		}
		node = pr.getProperty("LOG_ADDRESS");
		if (null != node) {
			Config.LOG_ADDRESS = node.trim();
		}
		node = pr.getProperty("CORE_CONFIG_DIR");
		if (null != node) {
			Config.CORE_CONFIG_DIR = node.trim();
		}

		node = pr.getProperty("SHARD_NUM");
		if (null != node) {
			try {
				Config.SHARD_NUM = Integer.parseInt(node.trim());
			} catch (Exception e) {
				e.printStackTrace();
				Config.SHARD_NUM = 100;
			}
		}
		node = pr.getProperty("REPLICATE_NUM");
		if (null != node) {
			try {
				Config.REPLICATE_NUM = Integer.parseInt(node.trim());
			} catch (Exception e) {
				e.printStackTrace();
				Config.REPLICATE_NUM = 100;
			}
		}
		node = pr.getProperty("MAX_PER_NODE_NUM");
		if (null != node) {
			try {
				Config.MAX_PER_NODE_NUM = Integer.parseInt(node.trim());
			} catch (Exception e) {
				e.printStackTrace();
				Config.MAX_PER_NODE_NUM = 100;
			}
		}
		node = pr.getProperty("MYSQL_URL");
		if (null != node) {
			Config.MYSQL_URL = node.trim();
		}
		node = pr.getProperty("MYSQL_USERNAME");
		if (null != node) {
			Config.MYSQL_USERNAME = node.trim();
		}
		node = pr.getProperty("MYSQL_PASSWORD");
		if (null != node) {
			Config.MYSQL_PASSWORD = node.trim();
		}
		node = pr.getProperty("SERVER_HOST");
		if (null != node) {
			Config.SERVER_HOST = node.trim();
		}
		node = pr.getProperty("SERVER_AUTH");
		if (null != node) {
			Config.SERVER_AUTH = node.trim();
		}
		node = pr.getProperty("SERVER_URL");
		if (null != node) {
			Config.SERVER_URL = node.trim();
		}
		node = pr.getProperty("SEND_USER");
		if (null != node) {
			Config.SEND__USER = node.trim();
		}
		node = pr.getProperty("SEND_USER_NAME");
		if (null != node) {
			Config.SEND__USER_NAME = node.trim();
		}
		node = pr.getProperty("SEND_USER_PASSWORD");
		if (null != node) {
			Config.SEND__USER_PASSWORD = node.trim();
		}
		node = pr.getProperty("BCSE_INFO");
		if (null != node) {
			Config.BCSE_INFO = node.trim();
		}
		node = pr.getProperty("ENCODING");
		if (null != node) {
			Config.ENCODING = node.trim();
		}
		node = pr.getProperty("METHOD");
		if (null != node) {
			Config.METHOD = node.trim();
		}
		node = pr.getProperty("CHAR_SEQUENCE");
		if (null != node) {
			Config.charSequence = node.trim().toCharArray();
		}
		node = pr.getProperty("SAVE_FILE_PATH");
		if (null != node) {
			Config.SAVE_FILE_PATH = node.trim();
		}
		node = pr.getProperty("LOG_FOLDER_NAME");
		if (null != node) {
			Config.LOG_FOLDER_NAME = node.trim();
		}
		node = pr.getProperty("RULE_NUM_LIMIT");
		if (null != node) {
			Config.RULE_NUM_LIMIT =Integer.parseInt(node.trim());
		}
	}

}
