package com.chinamobile.cmss.bcselogAnalyse.redis;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.chinamobile.cmss.bcselogAnalyse.tools.Config;

import redis.clients.jedis.Jedis;

public class SyncRedisData {
	
	public static void main(String[] args) {
		
		JSONObject data = new JSONObject();
		try {
			data.put("APP_ID", "");
			data.put("USER_ID", "");
			data.put("APP_NAME", "");
			data.put("APP_DESCRIBE", "");
			data.put("APP_STATUS", "");
			data.put("CREATE_DATE", "");
			data.put("TEMP_ID", "");
			data.put("APP_TEMP_FLAG", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		insertInfoToRedis("", data);
	}

	
	//插入redis
	public static void insertInfoToRedis(String key,JSONObject data){
		

		Jedis jedis = JedisUtil.getJedis(Config.REDIS_SERVER_IP, Config.REDIS_SERVER_PORT, Config.REDIS_PASSWD);
		jedis.lpush(key, data.toString());
		if (jedis != null) {
			JedisUtil.getPool(Config.REDIS_SERVER_IP, Config.REDIS_SERVER_PORT, Config.PASSWORD).returnResource(jedis);
		}
	
	}
}
