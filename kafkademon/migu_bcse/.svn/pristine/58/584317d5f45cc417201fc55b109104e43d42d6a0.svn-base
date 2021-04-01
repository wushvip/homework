package com.chinamobile.cmss.bcselogAnalyse.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.chinamobile.cmss.bcselogAnalyse.bean.Key;
import com.chinamobile.cmss.bcselogAnalyse.bean.Pair;
import com.chinamobile.cmss.bcselogAnalyse.bean.TopNPriorityQueue;
import com.chinamobile.cmss.bcselogAnalyse.statistic.WordStatistic;
import com.chinamobile.cmss.bcselogAnalyse.tools.Config;
import redis.clients.jedis.Jedis;

public class RedisOp {
	public static void main(String[] args) {

	}

	private static ArrayList<JSONObject> convertMapToJsonArray(HashMap<Key, TopNPriorityQueue> hashMap) {
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		Set<Key> sets = hashMap.keySet();
		for (Key key : sets) {
			TopNPriorityQueue topNPriorityQueue = hashMap.get(key);
			List<Pair> values = topNPriorityQueue.sortedList(false);
			for (Pair keyword : values) {
				try {
					if (!key.isEmpty()) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("userId", key.getUser_id());
						jsonObject.put("appId", key.getApp_id());
						jsonObject.put("operTime", WordStatistic.getPastDate(0));
						jsonObject.put("keywords", keyword.getWord());
						jsonObject.put("searchCount", keyword.getCount());
						jsonObject.put("flag", key.getFlag());
						jsonObjects.add(jsonObject);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObjects;

	}

	private static void insertIntoRedis(String key, JSONObject jsonObject) {
		Jedis jedis = JedisUtil.getJedis(Config.REDIS_SERVER_IP, Config.REDIS_SERVER_PORT, Config.REDIS_PASSWD);
		jedis.lpush(key, jsonObject.toString());
		if (jedis != null) {
			JedisUtil.getPool(Config.REDIS_SERVER_IP, Config.REDIS_SERVER_PORT, Config.PASSWORD).returnResource(jedis);
		}

	}

	public static void insertHotkeyDay(HashMap<Key, TopNPriorityQueue> hashMap) throws Exception {
		ArrayList<JSONObject> jsonObjects = convertMapToJsonArray(hashMap);
		if (!jsonObjects.isEmpty()) {
			for (JSONObject jsonObject : jsonObjects) {
				insertIntoRedis(jsonObject.getString("appId") + jsonObject.getString("userId") + "HOTWORD_DAY",
						jsonObject);
			}

		}
	}

	public static void insertHotkeyWeek(HashMap<Key, TopNPriorityQueue> hashMap) throws Exception {

		ArrayList<JSONObject> jsonObjects = convertMapToJsonArray(hashMap);
		if (!jsonObjects.isEmpty()) {
			for (JSONObject jsonObject : jsonObjects) {
				insertIntoRedis(jsonObject.getString("appId") + jsonObject.getString("userId") + "HOTWORD_WEEK",
						jsonObject);
			}

		}

	}

	public static void insertHotkeyMonth(HashMap<Key, TopNPriorityQueue> hashMap) throws Exception {
		ArrayList<JSONObject> jsonObjects = convertMapToJsonArray(hashMap);
		if (!jsonObjects.isEmpty()) {

			for (JSONObject jsonObject : jsonObjects) {
				insertIntoRedis(jsonObject.getString("appId") + jsonObject.getString("userId") + "HOTWORD_MONTH",
						jsonObject);
			}

		}
	}
}
