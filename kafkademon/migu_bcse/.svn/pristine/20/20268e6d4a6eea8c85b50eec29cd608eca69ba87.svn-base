package com.chinamobile.cmss.bcse.config.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.dao.FieldsDao;
import com.chinamobile.cmss.bcse.config.service.FieldsService;
import com.chinamobile.cmss.bcse.config.util.SolrConfigUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: FieldsServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:08:20
 */
@Service("fieldsService")
public class FieldsServiceImpl implements FieldsService {
	@Resource
	private FieldsDao fieldsDao;
	@Override
	public void show(String appId, String userId,ResultBean resultBean) {
		try {
			List<String> rankFields=fieldsDao.showRankFields(appId);
			List<String> suggestFields=fieldsDao.showSuggestFields(appId);
			List<String> showFields=fieldsDao.showShowFields(appId);
			List<String> facetFields=fieldsDao.showFacetFields(appId);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("roughFields", rankFields);
			jsonObject.put("suggestFields", suggestFields);
			jsonObject.put("showFields", showFields);
			jsonObject.put("facetFields", facetFields);
			resultBean.setResult(jsonObject);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.GetFieldsError);
			throw new RuntimeException(MsgConfig.GetFieldsError);
		}
	}
	/**
	 * 创建应用的时候需要添加selectHandler
	 * @Title: addSelectHandler 
	 * @Description: TODO
	 * @param appId
	 * @param userId
	 * @return: void
	 */
	public void addSelectHandler(String appId,String userId){
		try {
			StringBuffer qf=new StringBuffer();
			List<String> searchFields=fieldsDao.showRankFields(appId);
			for(String str:searchFields){
				qf.append(str).append(" ");
			}
			StringBuffer fl=new StringBuffer();
			List<String> showFields=fieldsDao.showShowFields(appId);
			for(String sf:showFields){
				fl.append(sf).append(" ");
			}
			SolrConfigUtil.addSelectHandler(appId, qf.toString(),fl.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
