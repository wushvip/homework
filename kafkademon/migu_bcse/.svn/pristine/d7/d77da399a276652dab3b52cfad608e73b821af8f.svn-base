package com.chinamobile.cmss.bcse.config.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.bean.Rule;
import com.chinamobile.cmss.bcse.config.bean.RuleType;
import com.chinamobile.cmss.bcse.config.bean.SuggestRuleReq;
import com.chinamobile.cmss.bcse.config.dao.SuggestRuleDao;
import com.chinamobile.cmss.bcse.config.service.SuggestRuleService;
import com.chinamobile.cmss.bcse.config.util.SearchHandler;
import com.chinamobile.cmss.bcse.config.util.SolrConfigUtil;
import com.chinamobile.cmss.bcse.config.util.TypeConvertor;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
/**
 * 
 * @ClassName: SuggestRuleServiceImpl 
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:09:35
 */
@Service("suggestRuleService")
public class SuggestRuleServiceImpl implements SuggestRuleService{
	
	@Resource
	private SuggestRuleDao suggestRuleDao;
	
	@Override
	public void insert(SuggestRuleReq req, ResultBean resultBean) {
		try {
			//业务校验
			if(!validate(req, resultBean)){
				return;
			}
			
			Rule rule=TypeConvertor.cast(req);
			suggestRuleDao.insert(rule);
			
		
			SearchHandler handler=getHandler(req);
			SolrConfigUtil.addSuggetHandler(req.getAppId(), handler);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.AddSuggestRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}

	@Override
	public void show(Map<String,Object> map,ResultBean resultBean) {
		try {
			List<Rule> ruleList=suggestRuleDao.show(map);
			List<SuggestRuleReq> reqList=new ArrayList<SuggestRuleReq>();
			for(Rule r:ruleList){
				SuggestRuleReq req=new SuggestRuleReq();
				req.setRuleName(r.getRuleName());
				req.setShowField(r.getShowField());
				String fields=r.getIncludeFields();
				String[] field=fields.split(";");
				List<String> includeFields=new ArrayList<String>();
				for(String str:field){
					includeFields.add(str);
				}
				req.setIncludeFields(includeFields);
				reqList.add(req);
			}
			JSONObject object=new JSONObject();
			object.put("ruleList", reqList);
			resultBean.setResult(object);
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.GetSuggestRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
		
	}

	@Override
	public void update(SuggestRuleReq req, ResultBean resultBean) {
		try {
			Rule rule=TypeConvertor.cast(req);
			Integer num=suggestRuleDao.update(rule);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_UPDATE_VALIDATION);
				return;
			}
			
			SearchHandler handler=getHandler(req);
			SolrConfigUtil.modifySuggestHandler(req.getAppId(), handler);
		} catch (Exception e) {
			LogUtil.loggerEntrance(req.getUserId(),req.getAppId(), Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.UpdateSuggestRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}

	@Override
	public void deleteByRuleName(Map<String,Object> map, ResultBean resultBean) {
		try {
			Integer num=suggestRuleDao.deleteByRuleName(map);
			if(num<1){
				resultBean.setMessage(MsgConfig.RULE_DELETE_VALIDATION);
				return;
			}
			String appId=map.get("appId").toString();
			String ruleName=map.get("ruleName").toString();
			String handlerName=Config.INTEL_ASSOCIATE_PREX+ruleName;
			SolrConfigUtil.deleteHandler(appId, handlerName);
			
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.DelSuggestRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	@Override
	public void deleteByRuleNames(Map<String,Object> map, ResultBean resultBean) {
		try {
			suggestRuleDao.deleteByRuleNames(map);
			String appId=map.get("appId").toString();
			
			@SuppressWarnings("unchecked")
			List<String> ruleNames=(List<String>)map.get("ruleNames");
			for(String ruleName:ruleNames){
				String handlerName=Config.INTEL_ASSOCIATE_PREX+ruleName;
				SolrConfigUtil.deleteHandler(appId, handlerName);
			}
			
		} catch (Exception e) {
			String userId=map.get("userId").toString();
			String appId=map.get("appId").toString();
			LogUtil.loggerEntrance(userId,appId, Config.FAIL_SERIVICE_CODE, 
					LogUtil.CONFIG_LOG, e,MsgConfig.BashDelSuggestRuleError);
			throw new RuntimeException(MsgConfig.SERIVICE_EXCP);
		}
	}
	
	
	private SearchHandler getHandler(SuggestRuleReq req){
		String ruleName=req.getRuleName();
		List<String> includes=req.getIncludeFields();
		StringBuffer qf=new StringBuffer();
		for(String str:includes){
			qf.append(str).append(" ");
		}
		qf.deleteCharAt(qf.length()-1);
		String showField=req.getShowField();
		SearchHandler handler=new SearchHandler();
		handler.setHandlerName(Config.INTEL_ASSOCIATE_PREX+ruleName);
		handler.setShowField(showField);
		handler.setQf(qf.toString());
		return handler;
	}

	
	/**
	 * 校验
	 * @param req
	 * @param resultBean
	 * @return
	 */
	private boolean validate(SuggestRuleReq req,ResultBean resultBean){
		boolean flag=false;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appId", req.getAppId());
		map.put("userId", req.getUserId());
		map.put("ruleType", RuleType.SUGGEST_RULE.getValue());
		Integer totalNum=suggestRuleDao.showNum(map);
		if(totalNum>=Config.RULE_NUM_LIMIT){
			resultBean.setMessage(MsgConfig.RULE_NUM_EXCEEDS);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		map.put("ruleName", req.getRuleName());
		Integer num=suggestRuleDao.showNum(map);
		if(num>0){
			resultBean.setMessage(MsgConfig.RULE_NAME_EXIST);
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setCode(Config.FAIL_SERIVICE_CODE);
			return flag;
		}
		flag=true;
		return flag;
	}

}
