package com.chinamobile.cmss.bcse.index.indexManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.cloud.Slice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.cleardata.ClearUpIndexData;
import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.GetDataSql;
import com.chinamobile.cmss.bcse.index.kafka.CsvToJson;
import com.chinamobile.cmss.bcse.index.kafka.GetFromKafka;
import com.chinamobile.cmss.bcse.index.service.impl.IndexDataOperaIndexServiceImpl.IndexOpType;
import com.chinamobile.cmss.bcse.index.solrConnection.ConnectSolrCore;
import com.chinamobile.cmss.bcse.index.tool.GetUniqueKeyFromProperties;
import com.chinamobile.cmss.bcse.index.tool.bigFileSplit;
import com.chinamobile.cmss.bcse.search.entry.DataApi;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.FileEncoding;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * 
 * @ClassName: UpdateIndex
 * @Description: 更新索引
 * @author: jinjing
 * @date: 2016年2月16日 下午3:18:32
 */
public class UpdateIndex {

	/*
	 * public static void main(String[] args) { String string =
	 * "{\"id\":\"1\",\"name\":\"q\\\"q\"}"; String string2="{id:1,name:2}";
	 * JSONObject jsonObject = JSONObject.fromObject(string);
	 * System.out.println(jsonObject.toString());
	 * 
	 * }
	 */

	/**
	 * 
	 * @Title: process
	 * @Description: 遍历文件，更新索引
	 * @param userId
	 * @param appId
	 * @param tableProperties
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String userId, String appId, ArrayList<TableProperty> tableProperties) {
		try {

			// 从数据库中得到数据
			String UpdateFile = GetDataSql.process(userId, appId, "", tableProperties);
			System.out.println("userId:" + userId + "appId:" + appId);

			// 清洗日期的格式
			if (!ClearUpIndexData.process(UpdateFile, tableProperties, appId, "add")) {
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

	public static boolean singleProcess(String userId, String appId, ArrayList<TableProperty> tableProperties,
			String filePath, String type) {
		try {
			System.out.println("userId:" + userId + "    appId:" + appId);
			// 转换源文件编码为utf-8
			FileEncoding.getFileEncoding(filePath);

			// 将文件切成小文件
			ArrayList<String> files = bigFileSplit.process(filePath);
			for (String file : files) {
				long start = System.currentTimeMillis();
				if (type.equals(IndexOpType.ADD)) {// 整条更新
					// 清洗数据
					if (!ClearUpIndexData.process(file, tableProperties, appId, "add")) {
						return false;
					}
				} else {// 部分更新
					if (!ClearUpIndexData.process(file, tableProperties, appId, "mod")) {
						return false;
					}
				}
				// 启动consumer消费kafka中的数据
				GetFromKafka.process(userId, appId, tableProperties);
				long middle = System.currentTimeMillis();
				System.out.println("清洗的时间：" + (middle - start));

				long end = System.currentTimeMillis();
				System.out.println("one file:" + (end - start));
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @Title: updateFile
	 * @Description: 调用solr的api更新索引
	 * @param FilePath
	 * @param CoreName
	 * @throws Exception
	 * @return: void
	 */
	public static void updateFile(String FilePath, String CoreName, TableProperty tableProperty) throws Exception {

		if (tableProperty == null) {
			throw new Exception();
		}

		CloudSolrClient cloudSolrClient = ConnectSolrCore.getCloudInstance(Config.ZK_HOST, CoreName);
		try {
			ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/csv");
			String contentType = "text/plain; charset=UTF-8";

			// 加入字段分隔符设置
			if (tableProperty != null) {
				up.setParam("separator", tableProperty.getFileSplit());
			}
			// 加入多值分隔符设置
			HashMap<String, TableField> fieldMap = tableProperty.getFieldMap();
			Set<Entry<String, TableField>> entrySet = fieldMap.entrySet();
			for (Entry<String, TableField> entry : entrySet) {
				TableField tableField = entry.getValue();
				if (tableField.getIsMutivalue().equals("1")) {
					up.setParam("f." + entry.getKey() + ".split", "true");
					up.setParam("f." + entry.getKey() + ".separator", tableProperty.getMutiValueSplit());
				}
			}

			up.addFile(new File(FilePath), contentType);

			UpdateResponse response = up.process(cloudSolrClient);
			if (response.getStatus() == 0) {
				UpdateResponse response1 = cloudSolrClient.commit();

				if (response1.getStatus() != 0) {
					LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "Solr commit false");
					Exception e = new Exception("Solr commit false");
					throw e;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			cloudSolrClient.close();
		}
	}

	/**
	 * 将kafka的json数据写入solr
	 * 
	 * @param coreName
	 * @param jsons
	 * @param machine
	 *            写入的机器
	 * @throws Exception
	 */
	public static void updateJson(String userId, String coreName, ArrayList<String> jsons,
			ArrayList<TableProperty> tableProperties) {
		File file = null;
		try {
			HashMap<String, TableField> table1 = tableProperties.get(0).getFieldMap();
			ArrayList<String> headers = new ArrayList<String>();
			Set<Entry<String, TableField>> entries = table1.entrySet();
			for (Entry<String, TableField> entry : entries) {
				if (entry.getValue().getSrcField().equals("")) {
					headers.add(entry.getKey());
				}
			}
			file = CsvToJson.reverse(jsons, headers, tableProperties.get(0));
			updateFile(file.getAbsolutePath(), coreName, tableProperties.get(0));
			System.out.println(file.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				file.delete();
			new DataApi().setNumToDataBase(userId, coreName);
		}

	}

	/**
	 * 部分更新的函数
	 * 
	 * @param userId
	 * @param coreName
	 * @param jsons
	 * @param tableProperties
	 * @throws Exception
	 */
	public static void updatePartField(String userId, String coreName, ArrayList<String> jsons,
			ArrayList<TableProperty> tableProperties) throws Exception {
		CloudSolrClient cloudSolrClient = ConnectSolrCore.getCloudInstance(Config.ZK_HOST, coreName);
		try {
			ArrayList<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();

			String uniqueKey = GetUniqueKeyFromProperties.get(tableProperties);
			if (uniqueKey == "") {
				throw new Exception();
			}
			HashMap<String, TableField> fieldMap = tableProperties.get(0).getFieldMap();

			for (String json : jsons) {
				JSONObject jsonObject = JSONObject.parseObject(json);
				SolrInputDocument solrInputDocument = new SolrInputDocument();
				@SuppressWarnings("unchecked")
				Set<String> keys = jsonObject.keySet();
				for (String key : keys) {
					if (!fieldMap.containsKey(key)) { // 动态字段，单独处理
						solrInputDocument.addField(uniqueKey, jsonObject.get(uniqueKey));
						solrInputDocument.addField(key, jsonObject.get(key));
						continue;
					}
					// 多值单独处理
					if (fieldMap.get(key).getIsMutivalue().equals("1")) {

						JSONArray value = jsonObject.getJSONArray(key);

						for (Object va : value) {

							SolrInputDocument solrInputDocument1 = new SolrInputDocument();
							Map<String, String> operation = new HashMap<String, String>();
							operation.put("add", va.toString());
							solrInputDocument1.addField(key, operation);
							solrInputDocument1.addField(uniqueKey, jsonObject.get(uniqueKey));
							solrInputDocuments.add(solrInputDocument1);
						}
						continue;
					}
					// 如果是主键
					if (key.equals(uniqueKey)) {

						continue;

					} else {
						solrInputDocument.setField(uniqueKey, jsonObject.get(uniqueKey));
						Map<String, String> operation = new HashMap<String, String>();
						operation.put("set", jsonObject.getString(key));
						solrInputDocument.addField(key, operation);

					}
				}
				if (solrInputDocument.size() > 0)
					solrInputDocuments.add(solrInputDocument);
			}

			try {
				if (solrInputDocuments.size() > 0)
					cloudSolrClient.add(solrInputDocuments);
			} catch (Exception e) {

				e.printStackTrace();
			}

			cloudSolrClient.commit();
			new DataApi().setNumToDataBase(userId, coreName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				cloudSolrClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
