package com.chinamobile.cmss.bcselogAnalyse.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

	/**
	 * Redis工具类,用于获取RedisPool. 参考官网说明如下： You shouldn't use the same instance
	 * from different threads because you'll have strange errors. And sometimes
	 * creating lots of Jedis instances is not good enough because it means lots
	 * of sockets and connections, which leads to strange errors as well. A
	 * single Jedis instance is not threadsafe! To avoid these problems, you
	 * should use JedisPool, which is a threadsafe pool of network connections.
	 * This way you can overcome those strange errors and achieve great
	 * performance. To use it, init a pool: JedisPool pool = new JedisPool(new
	 * JedisPoolConfig(), "localhost"); You can store the pool somewhere
	 * statically, it is thread-safe. JedisPoolConfig includes a number of
	 * helpful Redis-specific connection pooling defaults. For example, Jedis
	 * with JedisPoolConfig will close a connection after 300 seconds if it has
	 * not been returned.
	 * 
	 * @author wujintao
	 */

	/**
	 * 私有构造器.
	 */

	private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();
	private static Logger logger = Logger.getLogger(JedisUtil.class);

	/**
	 * 获取连接池.
	 * 
	 * @return 连接池实例
	 */
	synchronized static JedisPool getPool(String ip, int port, String passwd) {
		String key = ip + ":" + port;
		JedisPool pool = null;
		if (!maps.containsKey(key)) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(1024);
			config.setMaxIdle(200);

			config.setMaxWaitMillis(60000);
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			try {
				/**
				 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
				 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
				 * JedisPool默认的超时时间是2秒(单位毫秒)
				 */
				if (passwd.equals("")) {
					pool = new JedisPool(config, ip, port, 60000);
				} else {
					pool = new JedisPool(config, ip, port, 60000, passwd);
				}

				maps.put(key, pool);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			pool = maps.get(key);
		}
		return pool;
	}

	

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	public static Jedis getJedis(String ip, int port, String passwd) {
		Jedis jedis = null;
		JedisPool pool = null;
		int count = 0;
		do {
			try {
				pool = getPool(ip, port, passwd);
				jedis = pool.getResource();
				// log.info("get redis master1!");
			} catch (Exception e) {
				e.printStackTrace();
				// 销毁对象
				getPool(ip, port, passwd).returnBrokenResource(jedis);
			}
			count++;
		} while (jedis == null && count < 3);
		return jedis;
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param jedis
	 *            redis实例
	 */
	public void closeJedis(Jedis jedis, String ip, int port, String passwd) {
		if (jedis != null) {
			getPool(ip, port, passwd).returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: getKeys
	 * @Description: 模糊查询key
	 * @param key
	 * @return: void
	 */
	public void DeleteKeysBySearch(String ip, int port, String key, String passwd) {
		Jedis jedisConn = null;
		try {
			jedisConn = getJedis(ip, port, passwd);
			// 注意：当大数据量时，keys效率欠佳
			Set<String> keys = jedisConn.keys("*" + key + "*");
			if (keys == null || keys.size() <= 0) {
				logger.info("redis中未查到*" + key + "*相关的key");
				return;
			}
			logger.info("从redis中获取到key为*" + key + "*的数量为:" + keys.size());
			int m = 0;
			for (String keySingle : keys) {
				jedisConn.del(keySingle);
				m++;
			}
			logger.info("从redis中删除key为*" + key + "*的数量为:" + m);

		} catch (Exception e) {
			logger.error("定时任务中，删除redis热词key为*" + key + "*操作异常");
			logger.error("redis ip:" + ip + ", redis port:" + port);
			logger.error(e.getMessage(), e);
		} finally {
			closeJedis(jedisConn, ip, port, passwd);
		}

	}
}
