package com.chinamobile.cmss.bcse.index.indexManager;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;

import com.chinamobile.cmss.bcse.index.solrConnection.ConnectSolrCore;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: CreateCollection
 * @Description: 建立核
 * @author: jinjing
 * @date: 2016年2月16日 下午3:15:31
 */
public class CreateCollection {

	public static void main(String[] args) {
		try {
			System.out.println(CreateCollection.process("a425c0e43f054f5fb0b6386a64637b6b", 3, 2, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: process
	 * @Description: 建立入口
	 * @param core_name
	 * @param shard_num
	 * @param replicate_num
	 * @param max_perNode
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public static boolean process(String core_name, Integer shard_num, Integer replicate_num, Integer max_perNode)
			throws Exception {
		// 得到一个client实例
		CloudSolrClient client = ConnectSolrCore.getCloudInstance(Config.ZK_HOST, core_name);

		// 删除已存在的核
		deleteExistCore(core_name, client);

		// 建立新核
		CollectionAdminRequest.Create req = new CollectionAdminRequest.Create();
		req.setCollectionName(core_name);
		req.setConfigName(core_name);
		req.setMaxShardsPerNode(max_perNode);
		req.setReplicationFactor(replicate_num);
		req.setNumShards(shard_num);
		CollectionAdminResponse rsp = req.process(client);

		if (!rsp.isSuccess()) {
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, rsp.getResponse().toString());
			throw new RuntimeException("creating index failed, error: "+rsp.getResponse().toString());

		}
		return true;
	}

	/**
	 * 
	 * @Title: deleteExistCore
	 * @Description: 如果存在旧核，先删除
	 * @param core_name
	 * @param client
	 * @return: void
	 */
	private static void deleteExistCore(String core_name, CloudSolrClient client) {
		try {
			/*
			 * // 如果核存在 if (CoreAdminRequest.getStatus(core_name,
			 * client).getCoreStatus( core_name) != null) {
			 */
			// 删除核数据
			client.deleteByQuery("*:*");
			// 删除核
			CollectionAdminRequest.Delete delete = new CollectionAdminRequest.Delete();
			delete.setCollectionName(core_name);
			delete.process(client);
			// }
		} catch (Exception e) {
			// e.printStackTrace();
			// LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
		}
	}
}
