package com.chinamobile.cmss.bcse.search.cloudapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.AnalysisPhase;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.TokenInfo;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.search.bean.QueryBean;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.AnalysisException;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;

import org.apache.solr.client.solrj.response.FieldAnalysisResponse;

public class AnalysisSolrCloud {

	/**
	 * 给指定的语句分词。
	 * 
	 * @param sentence
	 *            被分词的语句
	 * @return 分词结果
	 * @throws IOException
	 * @throws SolrServerException
	 * @throws AnalysisException 
	 */
	public static void getAnalysis(QueryBean queryBean, ResultBean resultBean)
			throws  AnalysisException {

		FieldAnalysisRequest request = new FieldAnalysisRequest("/analysis/field");
		request.addFieldName(queryBean.getFieldSearch());// 字段名，随便指定一个支持中文分词的字段
		request.setFieldValue(queryBean.getSearchQuery());// 字段值，可以为空字符串，但是需要显式指定此参数
		request.setQuery(queryBean.getSearchQuery());
		List<String> results = new ArrayList<String>();
		Iterator<AnalysisPhase> it = null;
		FieldAnalysisResponse response = null;
		
		try {
			CloudSolrClient solrServer = ConnectSolrCloud.getCloudSolrServers(Config.ZK_HOST, queryBean.getAppId());
			response = request.process(solrServer);
			it = response.getFieldNameAnalysis(queryBean.getFieldSearch()).getQueryPhases().iterator();
		} catch (Exception e) {
			LogUtil.loggerEntrance(queryBean.getUserId(), queryBean.getAppId(), ExceptionConstants.AnalysisError, LogUtil.SEARCH_LOG, e);
			throw new AnalysisException(ExceptionConstants.AnalysisError);
		}
		
		AnalysisPhase pharse = (AnalysisPhase) it.next();
		List<TokenInfo> list = pharse.getTokens();
		for (TokenInfo info : list) {
			results.add(info.getText());
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("analysis", results);
		resultBean.setResult(jsonObject);
	}

}
