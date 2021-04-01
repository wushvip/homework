package com.chinamobile.cmss.bcse.search.entry;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;
import com.chinamobile.cmss.bcse.datastatistics.dao.DataInfoDao;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.search.cloudapi.ConnectSolrCloud;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

/**
 * @ClassName: DataApi
 * @Description: 获取数据量接口
 * @author: chenmin
 * @date: 2016年2月3日 上午11:40:46
 */
public class DataApi {

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_time = df.format(new Date()).toString();
		System.out.println(date_time);
		String userName = "0fa254e3-933a-4ccf-aa05-ef73e0598092";
		String appName = "e50a9911423643e7979c139ad6a6ddfa";
		new DataApi().setNumToDataBase(userName, appName);
	}

	/**
	 * @Title: setNumToDataBase
	 * @Description: 插入数据
	 * @param userId
	 * @param appId
	 * @return
	 * @return: boolean
	 */
	public boolean setNumToDataBase(String userId, String appId) {

		if (userId == null || userId.equals("")) {
			return false;
		}
		if (appId == null || appId.equals("")) {
			return false;
		}
		boolean flag = true;
		// 查詢索引
		QueryBean queryBean = new QueryBean();
		queryBean.setRankType("-1");
		queryBean.setUserId(userId);
		queryBean.setAppId(appId);
		queryBean.setQuery("*:*");
		try {
			CloudSolrClient cloudSolrServer = null;
			String collectionName = queryBean.getAppId();

			cloudSolrServer = ConnectSolrCloud.getCloudSolrServers(Config.ZK_HOST, collectionName);
			QueryResponse response = cloudSolrServer.query((SolrParams) queryBean, METHOD.POST);
			DataInfoBean dataInfoBean = new DataInfoBean();
			dataInfoBean.setAppId(appId);
			dataInfoBean.setUserId(userId);
			dataInfoBean.setDateTime(Tool.getCurrentDateHour());
			dataInfoBean.setSize((int) response.getResults().getNumFound());
			dataInfoBean.setFlag(1);

			DataInfoDao dataInfoDao = (DataInfoDao) Config.ac.getBean("dataInfoDao");
			// appFieldBeanDao=(AppFieldBeanDao) ac.getBean("appFieldBeanDao");
			dataInfoDao.insertdataNumberLatest(dataInfoBean);
		} catch (Exception e) {
			flag = false;
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.SearchError, LogUtil.SEARCH_LOG, e);

		}
		return flag;
	}

}
