package com.chinamobile.cmss.bcse.tool.tools;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
 * @ClassName: RedisPoolUtil 
 * @Description: 配有连接池的 Redis 工具类
 * @author: zhenglinfeng
 * @date: 2015年11月19日 下午3:04:24  
 */
public class RedisPoolUtil {

	/*// protected static Logger logger = Logger.getLogger(RedisPoolUtil.class);

	// Redis服务器IP
	private static String HOST = "";

	// Redis的端口号
	private static int PORT = 0;

	// 可用连接实例的最大数目，默认值为8；如果赋值为-1，则表示不限制；如果pool已经分配了
	// maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 2048;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 400;
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;
	// 超时时间，默认情况120秒
	private static int TIMEOUT = 100000;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；

	private static JedisPool jedisPool = null;
	private static boolean TEST_ON_BORROW = true;
	private static boolean TEST_ON_RETURN = true;

	public static void main(String[] args) {
		System.out.println("main method called");
		Jedis jedis = getJedis();
		jedis.select(0);
		System.out.println(jedis.dbSize());

	}
	
	*//** 
	 * @Title: initialPool 
	 * @Description: 初始化Redis连接池
	 * @return: boolean
	 *//*
	private static boolean initialPool() {
		try {
			HOST = Config.getRedisServerIp();
			PORT = Integer.parseInt(Config.getRedisServerPort());
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setTestOnBorrow(TEST_ON_BORROW);
			config.setTestOnReturn(TEST_ON_RETURN);
			jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT);
		} catch (Exception e) {
			LogUtil.loggerEntrance(ExceptionConstants.InitialRedisError, e.getMessage());
			return false;
		}
		return true;
	}

	*//**
	 * 在多线程环境同步初始化
	 *//*
	private static boolean poolInit() {
		if (jedisPool == null) {
			return initialPool();
		}
		return true;
	}

	*//** 
	 * @Title: getJedis 
	 * @Description: 同步获取Jedis实例
	 * @return: Jedis
	 *//*
	public synchronized static Jedis getJedis() {
		boolean isInitSuccess = true;
		if (jedisPool == null) {
			isInitSuccess = poolInit();
		}
		if (!isInitSuccess)
			return null;

		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
				return jedis;
			} else
				return null;
		} catch (Exception e) {
			if(jedis != null)
				returnBrokenResource(jedis);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, e.getMessage());
			
		}
		return jedis;
	}

	*//** 
	 * @Title: returnResource 
	 * @Description: 释放jedis资源
	 * @param jedis
	 * @return: void
	 *//*
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	*//** 
	 * @Title: returnBrokenResource 
	 * @Description: 释放jedis坏连接
	 * @param jedis
	 * @return: void
	 *//*
	public static void returnBrokenResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnBrokenResource(jedis);
		}
	}

	*//** 
	 * @Title: writeDataToRedis 
	 * @Description: 向redis写数据
	 * @param key
	 * @param object
	 * @param jedis
	 * @return: void
	 *//*
	public static void writeDataToRedis(String key, Object object, Jedis jedis) {
		jedis.set(key.getBytes(), SerializeUtil.serialize(object));
	}

	*//** 
	 * @Title: readDataFromRedis 
	 * @Description: 从redis获取数据
	 * @param key
	 * @param jedis
	 * @return: Object
	 *//*
	public static Object readDataFromRedis(String key, Jedis jedis) {
		return SerializeUtil.unserialize(jedis.get(key.getBytes()));
	}*/
}
