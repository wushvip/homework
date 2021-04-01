package com.chinamobile.cmss.bcse.index.indexManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import com.chinamobile.cmss.bcse.cleardata.ClearUpIndexData;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.GetDataSql;
import com.chinamobile.cmss.bcse.index.kafka.GetFromKafka;
import com.chinamobile.cmss.bcse.index.kafka.KafkaProduce;
import com.chinamobile.cmss.bcse.index.solrConnection.ConnectSolrCore;
import com.chinamobile.cmss.bcse.index.tool.GetUniqueKeyFromProperties;
import com.chinamobile.cmss.bcse.index.tool.ReadFromCsv;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.config.Config;

public class DeleteIndexFromFile {

	/**
	 * 多表应用删除部分索引
	 * 
	 * @param userId
	 * @param appId
	 * @param tableProperties
	 * @return
	 */
	public static boolean process(String userId, String appId, ArrayList<TableProperty> tableProperties) {
		try {
			// 从数据库中得到数据
			String deleteFile = GetDataSql.process(userId, appId, "", tableProperties);
			System.out.println("userId:" + userId + "appId:" + appId);

			// 清洗日期的格式
			if (!ClearUpIndexData.process(deleteFile, tableProperties, appId, "del")) {
				return false;
			}

			// 启动consumer消费kafka中的数据
			GetFromKafka.process(userId, appId, tableProperties);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 单表应用删除部分索引
	 * 
	 * @param userId
	 * @param appId
	 * @param tableProperties
	 * @param fileSplit
	 * @param mutivalueSplit
	 * @return
	 */
	public static boolean singleProcess(String userId, String appId, ArrayList<TableProperty> tableProperties,
			String filePath, String mutivalueSplit, String fileSplit) {
		try {

			String deleteFile = filePath;
			System.out.println("userId:" + userId + "appId:" + appId);
			String uniqueKey = GetUniqueKeyFromProperties.get(tableProperties);
			if (uniqueKey.equals("")) {
				return false;
			}

			// 从文件中读入数据
			List<String[]> lineList = ReadFromCsv.read(deleteFile, fileSplit.toCharArray()[0]);
			String[] head = lineList.get(0);
			int cloumNum = -1;
			// 获取uniqueKey是第几列
			for (int i = 0; i < head.length; i++) {
				System.out.println(head[i] + ":" + head[i].hashCode() + " " + head[i].length());
				System.out.println(uniqueKey + ":" + uniqueKey.hashCode() + " " + uniqueKey.length());
				if (head[i].equals(uniqueKey)) {
					cloumNum = i;
					break;
				}
			}
			if (cloumNum < 0) {
				System.out.println("主键没找到");
				return false;
			}
			ArrayList<String> results = new ArrayList<String>();
			for (int j = 1; j < lineList.size(); j++) {
				results.add(lineList.get(j)[cloumNum]);
			}

			KafkaProduce.process(appId, "del", results);
			// 启动consumer消费kafka中的数据
			if (results.size() != 0) {
				GetFromKafka.process(userId, appId, tableProperties);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 文件删除solr API删除的接口
	 * 
	 * @param deleteFile
	 * @param appId
	 * @return
	 *//*
		 * private static boolean fileDelete(String deleteFile, String appId,
		 * ArrayList<TableProperty> tableProperties) {
		 * 
		 * String uniqueKey = ""; int cloumn = -1;
		 * 
		 * char fileSplit = ','; // 获取主表主键为uniqueKey for (TableProperty
		 * tableProperty : tableProperties) { if
		 * (tableProperty.getIsMainTable().equals("1")) { // 主表 HashMap<String,
		 * TableField> fileMap = tableProperty.getFieldMap();
		 * Iterator<Entry<String, TableField>> it =
		 * fileMap.entrySet().iterator(); while (it.hasNext()) { Entry<String,
		 * TableField> entry = it.next(); if
		 * (entry.getValue().getKey().equals("1")) { uniqueKey = entry.getKey();
		 * } } } } if (uniqueKey.equals("")) { return false; }
		 * 
		 * // 获取文件 try { List<String[]> list = ReadFromCsv.read(deleteFile,
		 * fileSplit); // 获取文件头 String[] header = list.get(0); // 获取第几列是唯一键 for
		 * (int i = 0; i < header.length; i++) { if
		 * (header[i].equals(uniqueKey)) { cloumn = i; } } if (cloumn < 0) {
		 * return false; } ArrayList<String> ids = new ArrayList<String>(); //
		 * 开始删除 for (int i = 1; i < list.size(); i++) {
		 * ids.add(list.get(i)[cloumn]); }
		 * 
		 * delete(ids, uniqueKey, appId);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); return false; }
		 * 
		 * return true; }
		 */
	/**
	 * 调用api删除索引
	 * 
	 * @param uniqueKey
	 * @param content
	 * @param appId
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	private static boolean delete(String userId, ArrayList<String> ids, String uniqueKey, String appId)
			throws Exception {

		CloudSolrClient cloudSolrClient = ConnectSolrCore.getCloudInstance(Config.ZK_HOST, appId);
		try {
			cloudSolrClient.deleteById(ids);
			cloudSolrClient.commit();
			new DataApi().setNumToDataBase(userId, appId);
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			cloudSolrClient.close();
		}

	}

	/**
	 * 利用kafka里面的数据删除solr索引
	 * 
	 * @param deList
	 * @param appId
	 * @param tableProperties
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteJson(String userId, ArrayList<String> deList, String uniqueKey, String appId)
			throws Exception {

		return delete(userId, deList, uniqueKey, appId);

	}

}
