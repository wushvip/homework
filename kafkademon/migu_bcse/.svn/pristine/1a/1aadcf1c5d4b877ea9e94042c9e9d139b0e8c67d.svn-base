package com.chinamobile.cmss.bcse.index.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.tool.config.Config;

import kafka.admin.TopicCommand;

/**
 * 从kafka获取数据
 * 
 * @author jinjing
 *
 */
public class GetFromKafka {
	// 保证线程安全，避免重复写入
	private static HashMap<String, KafkaConsume> kafkaMap;
	static ThreadPoolExecutor pool;
	static ReentrantLock lock = new ReentrantLock();

	static {
		System.out.println("in static");
		// 每个topic维护一个consumer
		kafkaMap = new HashMap<String, KafkaConsume>();
		pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

	/**
	 * 同步方法，避免多线程同时操作一个topic
	 * 
	 * @param topic
	 * @param tableProperties
	 */
	public static void process(String userId, String topic, ArrayList<TableProperty> tableProperties) {
		// 加锁，防止一个topic创建多个consumer线程
		lock.lock();
		/**
		 * 如果后台线程不在则启动
		 */
		if (kafkaMap.containsKey(topic)) {// 不做任何操作
			KafkaConsume kafkaConsume = kafkaMap.get(topic);
			if (!kafkaConsume.isFlag()) { // 进程不在，重新创建一个
				kafkaConsume = new KafkaConsume(userId, topic, tableProperties);
				kafkaMap.put(topic, kafkaConsume);
				// 启动线程
				pool.submit(kafkaConsume);
			}
		} else {// 建立对应topic的线程并加入线程池
			KafkaConsume kafkaConsume = new KafkaConsume(userId, topic, tableProperties);
			kafkaMap.put(topic, kafkaConsume);
			// 启动线程
			pool.submit(kafkaConsume);
		}
		lock.unlock();
		return;
	}

	/**
	 * 停止consumer线程
	 * 
	 * @param topic
	 */
	public static void stop(String topic) {
		try {
			if (kafkaMap.containsKey(topic)) {
				System.out.println("now pool size:" + pool.getActiveCount());
				KafkaConsume kafkaConsume = kafkaMap.remove(topic);
				kafkaConsume.setFlag(false);
				System.out.println("now pool size:" + pool.getActiveCount());
			} else {
				System.out.println("have no topic");
				System.out.println("now pool size:" + pool.getActiveCount());
			}
			// 删除topic
			// deleteTopic(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteTopic(String topic) {
		String[] options = new String[] { "--delete", "--zookeeper", Config.ZK_HOST, "--topic", topic };
		TopicCommand.main(options);
	}

}
