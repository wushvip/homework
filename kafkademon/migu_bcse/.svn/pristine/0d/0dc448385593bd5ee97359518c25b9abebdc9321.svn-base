package com.chinamobile.cmss.bcse.config.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.AnalysisPhase;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.TokenInfo;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.AnalysisReq;
import com.chinamobile.cmss.bcse.config.service.AnalysisService;
import com.chinamobile.cmss.bcse.search.cloudapi.ConnectSolrCloud;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: AnalysisServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:08:03
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService{

	@Override
	public void analyse(AnalysisReq req, ResultBean resultBean) {
		FieldAnalysisRequest request = new FieldAnalysisRequest("/analysis/field");
		request.addFieldType(req.getFieldSearch());// 字段名，随便指定一个支持中文分词的字段
		request.setFieldValue(req.getSearchQuery());// 字段值，可以为空字符串，但是需要显式指定此参数
		request.setQuery(req.getSearchQuery());
		request.setMethod(METHOD.POST);
		List<String> results = new ArrayList<String>();
		Iterator<AnalysisPhase> it = null;
		FieldAnalysisResponse response = null;
		
		try {
			CloudSolrClient solrServer = ConnectSolrCloud.getCloudSolrServers(Config.ZK_HOST, req.getAppId());
			response = request.process(solrServer);
			
			it = response.getFieldTypeAnalysis(req.getFieldSearch()).getQueryPhases().iterator();
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(), req.getAppId(), Config.FAIL_SERIVICE_CODE, LogUtil.CONFIG_LOG, e,
					MsgConfig.GetAnalysisError);
			throw new RuntimeException(MsgConfig.GetAnalysisError);
		}
		AnalysisPhase pharse=null;
	    while(it.hasNext()){
	    	pharse=it.next();
	    }
	   
		List<TokenInfo> list = pharse.getTokens();
		for (TokenInfo info : list) {
			if (info.getText() !=null) {
				results.add(info.getText());
			}
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("analysis", results);
		resultBean.setResult(jsonObject);
		
	}

}
