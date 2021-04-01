package com.chinamobile.cmss.bcse.index.kafka;

import java.util.Properties;

import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 * kafka的配置
 * 
 * @author jinjing
 *
 */
public class KafkaProperites {
	private final static Properties ProducerProps = new Properties();
	private final static Properties ConsumerProps = new Properties();

	static {
		// broker的地址
		ProducerProps.put("bootstrap.servers", Config.BROKERS);
		// acks=1： 这意味着至少要等待leader已经成功将数据写入本地log，而不管follower是否成功写入。
		ProducerProps.put("acks", "1");
		// 数据发送失败，重发的次数
		ProducerProps.put("retries", 1);
		// key的序列化方式
		ProducerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// value的序列化方式
		ProducerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// broker的地址
		ConsumerProps.put("bootstrap.servers", Config.BROKERS);
		// 消费者的分组，为了避免重复消费，一定要保证在一组中
		ConsumerProps.put("group.id", "6");
		// 每一次能拉取到的最大字节数
		ConsumerProps.put("max.partition.fetch.bytes", "20485760");
		// 是否自动提交offset
		ConsumerProps.put("enable.auto.commit", "true");
		// 每次拉取的条数最大值
		ConsumerProps.put("max.poll.records", "5000");
		// 自动提交的时间间隔
		ConsumerProps.put("auto.commit.interval.ms", "1000");
		ConsumerProps.put("session.timeout.ms", "300000");
		// 新加入集群的cosumer的开始消费位置
		ConsumerProps.put("auto.offset.reset", "earliest");
		ConsumerProps.put("request.timeout.ms", "400000");
		ConsumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		ConsumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	}

	public static Properties getProducerConf() {
		return ProducerProps;
	}

	public static Properties getConsumerConf() {
		return ConsumerProps;
	}
}
