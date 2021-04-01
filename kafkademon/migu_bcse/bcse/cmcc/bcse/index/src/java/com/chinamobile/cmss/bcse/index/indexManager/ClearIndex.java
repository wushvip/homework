package com.chinamobile.cmss.bcse.index.indexManager;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;

import com.chinamobile.cmss.bcse.index.solrConnection.ConnectSolrCore;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 
 * @ClassName: ClearIndex 
 * @Description: 清空索引
 * @author: jinjing
 * @date: 2016年2月16日 下午3:15:06
 */
public class ClearIndex {

	public static void main(String[] args) {
		System.out.println(process("neu.qbing@qq.com_test"));
	}

	/**
	 * 
	 * @Title: process 
	 * @Description: 删除核以及对应的数据文件，zookeeper的配置文件
	 * @param CoreName
	 * @return
	 * @return: boolean
	 */
	public static boolean process(String CoreName) {
		try {
			CloudSolrClient client = ConnectSolrCore.getCloudInstance(
					Config.ZK_HOST, CoreName);
			// 如果核存在
			if (CoreAdminRequest.getStatus(CoreName, client).getCoreStatus(
					CoreName) != null) {
				// 删除核数据
				try {

					client.deleteByQuery("*:*");
					client.commit();
					System.out.println("delete");
				} catch (Exception e) {
					e.printStackTrace();
					LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
