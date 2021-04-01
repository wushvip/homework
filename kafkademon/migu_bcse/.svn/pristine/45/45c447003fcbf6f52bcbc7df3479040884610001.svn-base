package com.chinamobile.cmss.bcse.index.indexManager;

import java.util.ArrayList;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;

import com.chinamobile.cmss.bcse.index.kafka.GetFromKafka;
import com.chinamobile.cmss.bcse.index.solrConnection.ConnectSolrCore;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: DeleteCollection
 * @Description: 删除相应的核
 * @author: jinjing
 * @date: 2016年2月16日 下午3:17:38
 */
public class DeleteCollection {

	public static void main(String[] args) throws Exception {

		// 得到一个client实例/*
		CloudSolrClient client = ConnectSolrCore.getCloudInstance(Config.ZK_HOST, "root@cmcc.com_zhenglinfeng");

		CollectionAdminRequest.List list = new CollectionAdminRequest.List();
		CollectionAdminResponse rep = list.process(client);
		@SuppressWarnings("unchecked")
		ArrayList<String> list2 = (ArrayList<String>) rep.getResponse().get("collections");

		for (String string : list2) {
			System.out.println(string);
			System.out.println(process(string));
		}

		// process("root@cmcc.com_jinjing");
	}

	/**
	 * 
	 * @Title: process
	 * @Description: 删除核以及对应的数据文件
	 * @param CoreName
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String CoreName) {
		try {
			CloudSolrClient client = ConnectSolrCore.getCloudInstance(Config.ZK_HOST, CoreName);

			// 删除核数据
			try {
				client.deleteByQuery("*:*");
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
			}
			// 删除对应的kafka线程
			GetFromKafka.stop(CoreName);
			// 删除核
			CollectionAdminRequest.Delete delete = new CollectionAdminRequest.Delete();
			delete.setCollectionName(CoreName);
			delete.process(client);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
