package com.chinamobile.cmss.bcse.tool.tools;

import java.util.HashMap;
import java.util.Map;

import com.chinamobile.cmss.bcse.tool.config.Config;

import redis.clients.jedis.Jedis;

/**
 * redis类
 * 
 * @author Quan
 * 
 */
public class RedisUtil {

	private static Jedis jedis = null;

	/**
	 * 获取redis实例
	 * 
	 * @return
	 */
	public static synchronized Jedis getJedisInstance() {
		jedis = new Jedis(Config.getRedisServerIp(), new Integer(
			Config.getRedisServerPort()), 10000);
		return jedis;
	}
	
	/**
	 * 关闭redis
	 * 
	 * @param jedis
	 */
	public static void closeJedis(Jedis jedis) {
		if (jedis != null && jedis.isConnected()) {
			jedis.quit();
			jedis.disconnect();
		}
	}

	/**
	 * 向redis写数据
	 * 
	 * @param key
	 * @param object
	 * @param jedis
	 */
	public static void writeDataToRedis(String key, Object object,Jedis jedis) {
		jedis.set(key.getBytes(), SerializeUtil.serialize(object));
	}

	/**
	 * 从redis获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object readDataFromRedis(String key,Jedis jedis) {
		return SerializeUtil.unserialize(jedis.get(key.getBytes()));
	}
	
	/**
	 * 
	 * @param 从redis或数据库获取指定应用信息
	 * @return
	 */
/*	public static AppInfoDataBean getAppInfo(Map<String, Object> params){

		AbstractDataFactory dataFactory = new AppInfoDataFactory();
		AppInfoDataBean appInfoDataBean = (AppInfoDataBean)dataFactory.orderData(DataBeanType.APPINFO,params);
		
		AppInfoBean appInfoBean = (AppInfoBean)appInfoDataBean.get(RedisDataType.APPLICATIONINFO);
		return appInfoDataBean;
	} */
	


}
