package com.chinamobile.cmss.bcse.index.solrConnection;

import org.apache.solr.client.solrj.impl.CloudSolrClient;

/**
 * 
 * @ClassName: ConnectSolrCore
 * @Description: 连接solr
 * @author: jinjing
 * @date: 2016年2月16日 下午3:19:41
 */
public class ConnectSolrCore {

	private static final int zkClientTimeout = 20000;
	private static final int zkConnectTimeout = 10000;

	/**
	 * 
	 * @Title: getCloudInstance
	 * @Description: 获取cloud的操作实例
	 * @param zkHost
	 * @param collectionName
	 * @return
	 * @return: CloudSolrClient
	 */
	public static CloudSolrClient getCloudInstance(String zkHost, String collectionName) {

		CloudSolrClient client = new CloudSolrClient(zkHost);
		client.setDefaultCollection(collectionName);
		client.setZkClientTimeout(zkClientTimeout);
		client.setZkConnectTimeout(zkConnectTimeout);
		client.connect();
		return client;
	}

}
