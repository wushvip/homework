package com.chinamobile.cmss.bcse.index.kafka;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.jetty.util.BlockingArrayQueue;

import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 * kafka写入
 * 
 * @author jinjing
 *
 */
public class KafkaProduce implements Runnable {

	// 阻塞队列
	static BlockingQueue<Runnable> workQueue = new BlockingArrayQueue<Runnable>(1000000);
	// 最多有KAFKA_PRODUCER_NUM个线程同时运行
	static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Config.KAFKA_PRODUCER_NUM,
			Config.KAFKA_PRODUCER_NUM, 60L, TimeUnit.SECONDS, workQueue);
	String topic;
	String op;
	ArrayList<String> values;

	public static void process(String topic, String op, ArrayList<String> values) {
		// 新建一个处理线程放入线程池
		KafkaProduce kafkaProduce = new KafkaProduce(topic, op, values);
		threadPoolExecutor.execute(kafkaProduce);
	}

	/**
	 * 将csv转变成json格式写入kafka
	 * 
	 * @param topic
	 *            应用id
	 * @param op
	 *            操作类型
	 * 
	 * @param values
	 *            json数据
	 * @throws Exception
	 */
	KafkaProduce(String topic, String op, ArrayList<String> values) {
		this.topic = topic;
		this.op = op;
		this.values = values;
	}

	@Override
	public void run() {
		// 设置配置属性
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(KafkaProperites.getProducerConf());
		try {
			// 如果topic不存在，则会自动创建，默认replication-factor为1，partitions个数为1
			for (String value : values) {
				ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, op, value);
				producer.send(data);
			}
		} finally {
			producer.close();
		}
	}
}