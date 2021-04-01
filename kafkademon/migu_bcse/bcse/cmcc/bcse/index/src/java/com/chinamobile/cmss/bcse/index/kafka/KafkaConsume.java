package com.chinamobile.cmss.bcse.index.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.indexManager.DeleteIndexFromFile;
import com.chinamobile.cmss.bcse.index.indexManager.UpdateIndex;
import com.chinamobile.cmss.bcse.index.tool.GetUniqueKeyFromProperties;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * kafka的消费者，每个appid对应一个
 * 
 * @author jinjing
 *
 */
public class KafkaConsume implements Callable<Object> {

	private String topic = "";
	KafkaConsumer<String, String> consumer;
	ArrayList<TableProperty> tableProperties;
	String userId;

	private volatile boolean flag = true;
	private boolean exist = false;

	public KafkaConsume(String userId, String topic, ArrayList<TableProperty> tableProperties) {
		this.tableProperties = tableProperties;
		this.topic = topic;
		this.userId = userId;
	}

	@Override
	public Object call() throws Exception {
		try {
			Thread.currentThread().setName(topic);
			int count = 0;
			// 初始化当前topic的consumer
			consumer = new KafkaConsumer<String, String>(KafkaProperites.getConsumerConf());
			consumer.subscribe(Arrays.asList(topic));
			consumer.commitSync();
			while (true) {
				if (!isFlag()) {// 有终止信号，退出程序
					exist = true;
					break;
				}
				ConsumerRecords<String, String> records = consumer.poll(5000);
				count = records.count();
				System.out.println("count:"+count);
				if (count == 0) {
					continue;
				}
				System.out.println("record :" + count);
				// 提交一下当前的偏移量
				consumer.commitSync();
				ArrayList<String> addlist = new ArrayList<String>();
				ArrayList<String> dellist = new ArrayList<String>();
				ArrayList<String> modlist = new ArrayList<String>();
				// 遍历拉取到的数据，并加入相应的操作集合
				for (ConsumerRecord<String, String> record : records) {
					switch (record.key()) { // 判断当前数据的操作类型
					case "del":
						dellist.add(record.value());
						break;
					case "add":
						addlist.add(record.value());
						break;
					case "mod":
						modlist.add(record.value());
						break;
					}
				}
				if (!dellist.isEmpty()) { // 执行删除操作
					try {
						DeleteIndexFromFile.deleteJson(userId, dellist, GetUniqueKeyFromProperties.get(tableProperties),
								topic);
					} catch (Exception e) {
						LogUtil.loggerEntrance(userId, topic, ExceptionConstants.IndexDeleteDataError,
								LogUtil.INDEX_LOG, e);

					}
				}
				if (!addlist.isEmpty()) { // 执行插入操作
					try {
						UpdateIndex.updateJson(userId, topic, addlist, tableProperties);
					} catch (Exception e) {
						LogUtil.loggerEntrance(userId, topic, ExceptionConstants.IndexUpdateDataError,
								LogUtil.INDEX_LOG, e);
					}
				}

				if (!modlist.isEmpty()) // 部分字段更新
				{
					try {
						UpdateIndex.updatePartField(userId, topic, modlist, tableProperties);
					} catch (Exception e) {
						LogUtil.loggerEntrance(userId, topic, ExceptionConstants.IndexUpdateDataError,
								LogUtil.INDEX_LOG, e);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {

	}

	public boolean isFlag() {
		return flag;
	}

	public synchronized boolean setFlag(boolean flag) {
		this.flag = flag;
		while (!exist)
			;
		return true;

	}

}
